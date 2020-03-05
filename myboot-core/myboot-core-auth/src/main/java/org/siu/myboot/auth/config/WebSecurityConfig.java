package org.siu.myboot.auth.config;

import org.siu.myboot.auth.jwt.JWTConfigurer;
import org.siu.myboot.auth.jwt.JwtAccessDeniedHandler;
import org.siu.myboot.auth.jwt.JwtAuthenticationEntryPoint;
import org.siu.myboot.auth.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurity 配置
 *
 * @Author Siu
 * @Date 2020/3/4 16:13
 * @Version 0.0.1
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * token工具
     */
    private final TokenProvider tokenProvider;

    /**
     * 认证入口点
     */
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;

    /**
     * 认证用户无权限访问的处理
     */
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public WebSecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }


    /**
     * 加密工具类
     *
     * @return
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
                .antMatchers("/v1/api/auth").permitAll()
                // 开放注册
                .antMatchers("/v1/admin/user/register").permitAll()
                // 开发放swagger
                .antMatchers("**/swagger*/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**/**").permitAll()
                // .antMatchers("/api/activate").permitAll()
                // .antMatchers("/api/account/reset-password/init").permitAll()
                // .antMatchers("/api/account/reset-password/finish").permitAll()

                // 设置API接口对应的权限
                // TODO 动态加载权限如何实现？
                .antMatchers("/api/普通用户的接口xxxx").hasAuthority("ROLE_USER")
                .antMatchers("/api/管理员接口xxx").hasAuthority("ROLE_ADMIN")
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
    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
