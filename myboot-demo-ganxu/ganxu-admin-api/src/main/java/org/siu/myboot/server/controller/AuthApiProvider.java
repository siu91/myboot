package org.siu.myboot.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试nacos 远程调用
 *
 * @Author Siu
 * @Date 2020/3/11 13:52
 * @Version 0.0.1
 */
@RestController
public class AuthApiProvider {

    private final RestTemplate restTemplate;

    @Autowired
    public AuthApiProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/echo")
    public String echo() {
        // ganxu-auth-api 为nacos 中注册的服务名
        return restTemplate.getForObject("http://ganxu-auth-api/echo", String.class);
    }

}
