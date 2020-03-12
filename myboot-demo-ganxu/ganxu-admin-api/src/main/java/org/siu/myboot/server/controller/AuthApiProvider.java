package org.siu.myboot.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试nacos修改配置
 *
 * @Author Siu
 * @Date 2020/3/11 13:52
 * @Version 0.0.1
 */
@RestController
public class AuthApiProvider {


    @GetMapping("/echo")
    public String echo() {
        return "hello Nacos Discovery ";
    }

}
