package org.siu.myboot.auth.jwt;


import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.constant.Constant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @Author Siu
 * @Date 2020/3/4 15:15
 * @Version 0.0.1
 */
@Slf4j
public class JWTFilter extends GenericFilterBean {


    private TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = getToken(httpServletRequest);
        String uri = httpServletRequest.getRequestURI();

        // 验证token
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // token 验证通过
            // 1、提取token中携带的权限标识
            // 2、把token中携带的用户权限放入SecurityContextHolder交由  Spring Security管理
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), uri);
        } else {
            log.debug("no valid JWT token found, uri: {}", uri);
        }

        filterChain.doFilter(servletRequest, servletResponse);
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


