package org.siu.myboot.auth.config;

import org.siu.myboot.auth.jwt.TokenFilter;
import org.siu.myboot.auth.jwt.TokenProvider;
import org.siu.myboot.auth.trace.TraceFilter;
import org.siu.myboot.component.cache.redis.RedisService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * BaseSecurityConfigurer 配置
 * 1、配置追踪ID
 * 2、配置token认证拦截器
 *
 * @Author Siu
 * @Date 2020/3/4 15:21
 * @Version 0.0.1
 */
public class BaseSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;

    private RedisService redisService;

    public BaseSecurityConfigurerAdapter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public BaseSecurityConfigurerAdapter(TokenProvider tokenProvider, RedisService redisService) {
        this.tokenProvider = tokenProvider;
        this.redisService = redisService;
    }

    @Override
    public void configure(HttpSecurity http) {
        TokenFilter tokenFilter = new TokenFilter(tokenProvider, redisService);
        // 把JWTFilter 放在默认Spring Security UsernamePasswordAuthenticationFilter 前面
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置追踪ID
        TraceFilter traceFilter = new TraceFilter();
        http.addFilterBefore(traceFilter, TokenFilter.class);
    }
}
