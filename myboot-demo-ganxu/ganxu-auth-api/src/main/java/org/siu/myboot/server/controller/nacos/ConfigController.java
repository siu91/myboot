package org.siu.myboot.server.controller.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class ConfigController {

    @Value("${siu.test.config1}")
    private String config1;

    /**
     * http://localhost:8080/config/get
     */
    @GetMapping("/get")
    public String get() {
        return config1;
    }

    @GetMapping("/echo")
    public String echo() {
        return "hello Nacos Discovery ";
    }

}
