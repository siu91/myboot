package org.siu.myboot.auth.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * AccessDeineHandler 用来解决【认证过】的用户访问无权限资源时的异常
 *
 * @Author Siu
 * @Date 2020/3/4 16:09
 * @Version 0.0.1
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without the necessary authorization
        // We should just send a 403 Forbidden response because there is no 'error' page to redirect to
        // Here you can place any message you want
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}

