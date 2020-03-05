package org.siu.myboot.server.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.exception.UserNotActivatedException;
import org.siu.myboot.server.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.siu.myboot.server.entity.po.User;

/**
 * @Author Siu
 * @Date 2020/3/4 15:26
 * @Version 0.0.1
 */
@Slf4j
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Resource
    private UserRepository repository;

    /**
     * @param userLoginId 用户登录的ID（用户、手机等）
     * @return
     */
    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(final String userLoginId) {
        log.debug("Authenticating user '{}'", userLoginId);

        User user = repository.findByUserNameOrPhone(userLoginId, userLoginId);
        String lowercaseLogin = userLoginId.toLowerCase(Locale.ENGLISH);
        return createSpringSecurityUser(lowercaseLogin, user);

    }

    /**
     * @param lowercaseLogin
     * @param user
     * @return
     */
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) throws UserNotActivatedException {
        // 用户未激活
        if (user.getDeleteStatus() > 0) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }

        // 用户的权限信息
        // 对应WebSecurityConfig中的配置  .antMatchers("/api/普通用户的接口xxxx").hasAuthority("ROLE_USER")
        List<GrantedAuthority> grantedAuthorities = new ArrayList<String>() {
            // TODO 从数据库配置
            {
                add("ROLE_USER");
                add("ROLE_ADMIN");
            }
        }.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
