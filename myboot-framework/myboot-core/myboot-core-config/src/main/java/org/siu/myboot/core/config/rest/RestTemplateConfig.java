package org.siu.myboot.core.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Siu
 * @Date 2020/3/9 10:04
 * @Version 0.0.1
 */
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

