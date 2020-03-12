package org.siu.myboot.server.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.siu.myboot.core.entity.AuthUser;
import org.siu.myboot.core.exception.UserNotActivatedException;
import org.siu.myboot.server.entity.dto.UserAuthorities;
import org.siu.myboot.server.repository.UserRepository;
import org.siu.myboot.server.repository.dsl.UserRepositoryQueryDsl;
import org.siu.myboot.server.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private UserRepositoryQueryDsl userRepositoryQueryDsl;

    @Resource
    private UserService userService;

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
        List<UserAuthorities> userAuthoritiesList = userRepositoryQueryDsl.findUserAuthorities(userLoginId);
        userService.signIn(userLoginId, user.getVersion());
        String lowercaseLogin = userLoginId.toLowerCase(Locale.ENGLISH);
        return createSpringSecurityUser(lowercaseLogin, user, userAuthoritiesList);

    }

    /**
     * @param lowercaseLogin
     * @param user
     * @param userAuthoritiesList
     * @return
     * @throws UserNotActivatedException
     */
    private AuthUser createSpringSecurityUser(String lowercaseLogin, User user, List<UserAuthorities> userAuthoritiesList) throws UserNotActivatedException {
        // 用户未激活
        if (user.getDeleteStatus() > 0) {
            throw new UserNotActivatedException("用户[" + lowercaseLogin + "] 未激活");
        }

        Set<String> tmp = new HashSet<>();
        for (UserAuthorities authorities : userAuthoritiesList) {
            if (authorities.getRole() != null) {
                tmp.add(authorities.getRole());
            }
            if (authorities.getPermit() != null) {
                tmp.add(authorities.getPermit());
            }
        }

        // 用户的权限信息
        // 对应WebSecurityConfig中的配置  .antMatchers("/api/普通用户的接口xxxx").hasAuthority("ROLE_USER")
        List<GrantedAuthority> grantedAuthorities = new ArrayList<String>() {
            {
                addAll(tmp);
            }
        }.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new AuthUser(user.getUserName(), user.getPassword(), grantedAuthorities, user.getVersion());
    }
}
