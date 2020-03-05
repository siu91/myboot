package org.siu.myboot.auth.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * JWTConfigurer 配置 JWTConfigurer
 *
 * @Author Siu
 * @Date 2020/3/4 15:21
 * @Version 0.0.1
 */
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;

    public JWTConfigurer(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        TokenCheckFilter customFilter = new TokenCheckFilter(tokenProvider);
        // 把JWTFilter 放在默认Spring Security UsernamePasswordAuthenticationFilter 前面
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
