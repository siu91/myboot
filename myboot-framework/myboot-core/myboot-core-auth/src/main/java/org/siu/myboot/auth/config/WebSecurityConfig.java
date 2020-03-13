package org.siu.myboot.auth.config;

import org.siu.myboot.auth.jwt.JwtAccessDeniedHandler;
import org.siu.myboot.auth.jwt.JwtAuthenticationEntryPoint;
import org.siu.myboot.auth.jwt.TokenProvider;
import org.siu.myboot.component.cache.redis.RedisService;
import org.siu.myboot.core.constant.Constant;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * WebSecurity 配置
 *
 * @Author Siu
 * @Date 2020/3/4 16:13
 * @Version 0.0.1
 */
@EnableConfigurationProperties(SpringSecurityProperties.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * token工具
     */
    private final TokenProvider tokenProvider;

    private final RedisService redisService;

    /**
     * 认证入口点
     */
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;

    /**
     * 认证用户无权限访问的处理
     */
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final SpringSecurityProperties springSecurityProperties;

    public WebSecurityConfig(TokenProvider tokenProvider, RedisService redisService, JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler, SpringSecurityProperties springSecurityProperties) {
        this.tokenProvider = tokenProvider;
        this.redisService = redisService;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.springSecurityProperties = springSecurityProperties;
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")

                // allow anonymous resource requests
                .antMatchers(
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/h2-console/**",
                        "/swagger-ui.html/**",
                        "/swagger-resources/**"
                );
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final int permitSize = springSecurityProperties.getPermitAll().size();
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                // .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                // 设置认证入口点
                .authenticationEntryPoint(authenticationErrorHandler)
                // 设置认证用户无权限访问的处理
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // create no session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // 开放权限的接口（登录/注册等）
                .antMatchers(springSecurityProperties.getPermitAll().toArray(new String[permitSize])).permitAll()
                /* 设置API接口对应的权限
                .antMatchers("/api/普通用户的接口xxxx").hasAuthority("ROLE_USER")
                .antMatchers("/api/管理员接口xxx").hasAuthority("ROLE_ADMIN")*/
                // 要求所有进入应用的HTTP请求都要进行认证
                .anyRequest().authenticated()
                .and()
                .apply(securityConfigurerAdapter());

    }


    /**
     * 设置 SecurityConfigurerAdapter
     *
     * @return
     */
    private BaseSecurityConfigurerAdapter securityConfigurerAdapter() {
        return new BaseSecurityConfigurerAdapter(tokenProvider, redisService);
    }
}
