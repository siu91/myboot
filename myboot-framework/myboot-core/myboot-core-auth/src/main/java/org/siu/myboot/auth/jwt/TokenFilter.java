package org.siu.myboot.auth.jwt;


import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.auth.event.RequestEvent;
import org.siu.myboot.auth.event.RequestLog;
import org.siu.myboot.component.cache.redis.RedisService;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.utils.SpringContextHolder;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String jwt = getToken(httpServletRequest);
        String uri = httpServletRequest.getRequestURI();

        if (!Constant.Auth.NO_CHECK_API.contains(uri) && !ignoreCheckTokenWhenUriAnyMatch(uri)) {
            // 验证token
            Token token = tokenProvider.validate(jwt);

            // 记录系统请求事件：其它具体操作接口的行为也可以用【切面+注解+事件】方式记录
            RequestLog requestLog = new RequestLog();
            SpringContextHolder.publishEvent(new RequestEvent(requestLog));

            // 验证token版本
            Object oToken = redisService.get(Constant.RedisKey.USER_AUTH_KEY + token.getUsername());
            if (oToken != null) {
                long currentAuthVersion = Long.parseLong(oToken.toString());
                if (currentAuthVersion == 0) {
                    log.warn("用户已退出登录，请重新登录");
                    httpServletRequest.getRequestDispatcher(Constant.Auth.AUTH_ERROR_API + "用户已退出登录，请重新登录").forward(httpServletRequest, servletResponse);
                    return;
                }
                // token中存在auth 版本，但版本过期了
                if (token.getAuthVersion() >= 0 && token.getAuthVersion() < currentAuthVersion) {
                    log.warn("用户认证信息版本过期，请重新登录");
                    httpServletRequest.getRequestDispatcher(Constant.Auth.AUTH_ERROR_API + "用户认证信息版本过期，请重新登录").forward(httpServletRequest, servletResponse);
                    return;
                }
            }

            if (token.isAuthorized()) {
                // 校验通过，如果token接近过期，可以在这里重新根据业务情况颁发新的token给客户端
                refreshToken(httpServletResponse, token);
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
     * 忽略token校验，当uri匹配时
     *
     * @param uri
     * @return
     */
    private boolean ignoreCheckTokenWhenUriAnyMatch(String uri) {
        if (StringUtils.hasText(uri)) {
            return Constant.Auth.NO_CHECK_TOOLS_API.stream().anyMatch(uri::contains);
        }
        return false;
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


