package org.siu.myboot.auth.jwt;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.component.cache.redis.RedisService;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.exception.AuthenticateFail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author Siu
 * @Date 2020/3/4 15:15
 * @Version 0.0.1
 */
@Slf4j
public class TokenFilter extends GenericFilterBean {


    private RedisService redisService;

    private TokenProvider tokenProvider;

    public TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public TokenFilter(TokenProvider tokenProvider, RedisService redisService) {
        this.redisService = redisService;
        this.tokenProvider = tokenProvider;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String jwt = getToken(httpServletRequest);
        String uri = httpServletRequest.getRequestURI();

        if (!"/error".equals(uri) && !"/v1/api/auth".equals(uri) && !"/v1/api/auth/error".equals(uri)) {
            // 验证token
            Token token = tokenProvider.validate(jwt);
            // 验证token版本
            long authVersion = Long.parseLong(redisService.get(Constant.RedisKey.USER_AUTH_KEY + token.getUsername()).toString());
            if (token.getAuthVersion() >= 0) {
                if (token.getAuthVersion() < authVersion) {
                    log.warn("用户认证信息版本过期，请重新登录");
                    httpServletRequest.getRequestDispatcher(Constant.Auth.AUTH_ERROR_API + "用户认证信息版本过期，请重新登录").forward(httpServletRequest, servletResponse);
                    return;
                    //throw new AuthenticateFail("用户认证信息版本过期，请重新登录");
                }
            }

            if (StringUtils.hasText(jwt) && token.isAuthorized()) {
                refreshToken(httpServletResponse, token);
                // TODO token 校验通过，如果token接近过期，可以在这里重新根据业务情况颁发新的token给客户端
                // token 验证通过
                // 1、提取token中携带的权限标识
                // 2、把token中携带的用户权限放入SecurityContextHolder交由  Spring Security管理
                Authentication authentication = token.authentication();
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), uri);
                log.info("Authenticated user access:[{}]-[{}]", token.getClaimsJws().getBody().getSubject(), uri);
            } else {
                log.debug("no valid JWT token found, uri: {}", uri);
            }
        }

        filterChain.doFilter(servletRequest, httpServletResponse);
    }

    /**
     * 刷新token
     *
     * @param httpServletResponse
     * @param token
     */
    private void refreshToken(HttpServletResponse httpServletResponse, Token token) {
        String newJWT = tokenProvider.refresh(token);
        if (StringUtils.hasText(newJWT)) {
            httpServletResponse.setHeader(Constant.Auth.AUTHORIZATION_HEADER, Constant.Auth.TOKEN_PREFIX + newJWT);
        }

    }

    /**
     * 从请求头中获取token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constant.Auth.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.Auth.TOKEN_PREFIX)) {
            return bearerToken.substring(Constant.Auth.TOKEN_PREFIX.length());
        }
        return null;
    }


}


