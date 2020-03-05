package org.siu.myboot.auth.jwt;

import lombok.SneakyThrows;
import org.siu.myboot.core.exception.AuthenticateFail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证入口点
 * <p>
 * AuthenticationEntryPoint 用来解决【匿名用户】访问无权限资源时的异常
 * <p>
 * 如：  response.sendRedirect("/login");
 * 前后端分离，可以让前端处理即可
 *
 * @Author Siu
 * @Date 2020/3/4 16:04
 * @Version 0.0.1
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // Here you can place any message you want
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

        // 认证失败
        String uri = request.getRequestURI();
        throw new AuthenticateFail("无法获取[" + uri + "]");


    }
}

