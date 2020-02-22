package org.siu.myboot.component.sbaserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Siu
 * @Date 2020/2/22 9:49
 * @Version 0.0.1
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class SpringBootAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminServer.class, args);
    }
}

