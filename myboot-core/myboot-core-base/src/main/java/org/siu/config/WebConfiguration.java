package org.siu.config;

import org.siu.interceptor.DemoFilter;
import org.siu.interceptor.DemoFilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 拦截器/过滤器 配置
 *
 * @Author Siu
 * @Date 2020/2/16 15:00
 * @Version 0.0.1
 */
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> filterRegistration() {

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new DemoFilter());
        registration.addUrlPatterns("/*");
        registration.setName("DemoFilter");
        registration.setOrder(6);

        return registration;
    }


    @Bean
    public FilterRegistrationBean<Filter> filterRegistration1() {

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();

        registration.setFilter(new DemoFilter1());
        registration.addUrlPatterns("/*");
        registration.setName("DemoFilter1");
        registration.setOrder(1);

        return registration;
    }
}

