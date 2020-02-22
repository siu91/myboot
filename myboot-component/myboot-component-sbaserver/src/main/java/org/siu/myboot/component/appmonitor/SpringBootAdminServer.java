package org.siu.myboot.component.appmonitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Siu
 * @Date 2020/2/22 9:49
 * @Version 0.0.1
 */
@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminServer.class, args);
    }
}

