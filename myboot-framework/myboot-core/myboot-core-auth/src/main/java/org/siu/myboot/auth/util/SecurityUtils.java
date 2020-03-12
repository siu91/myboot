package org.siu.myboot.auth.util;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @Author Siu
 * @Date 2020/3/4 16:01
 * @Version 0.0.1
 */
@Slf4j
public class SecurityUtils {

    /**
     * 获取当前用户的登录ID（用户名、手机等）
     *
     * @return
     */
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("no authentication in security context found");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        }

        log.debug("found username '{}' in security context", username);

        return Optional.ofNullable(username);
    }


    /**
     * 获取当前用户的登录信息
     *
     * @return
     */
    public static Optional<AuthUser> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("no authentication in security context found");
            return Optional.empty();
        }

        AuthUser user = null;

        if (authentication.getPrincipal() instanceof AuthUser) {
            user = (AuthUser) authentication.getPrincipal();
        }


        return Optional.ofNullable(user);
    }
}

