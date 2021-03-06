package org.siu.myboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类 @SpringBootApplication
 * 启用swagger @EnableSwagger2
 * 在启动类中添加对 Mapper 包扫描 @MapperScan，或者直接在 Mapper 类上面添加注解 @
 * @EnableJpaAuditing 实现DO属性自动填充
 *
 * 作为 provider 启动
 *
 *
 * @Author Siu
 */
@EnableSwagger2
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class GanxuAuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GanxuAuthApiApplication.class, args);
    }

}
