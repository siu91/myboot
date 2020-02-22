package org.siu.myboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类 @SpringBootApplication
 * 启用swagger @EnableSwagger2
 * 在启动类中添加对 Mapper 包扫描 @MapperScan，或者直接在 Mapper 类上面添加注解 @Mapper
 *
 * @Author Siu
 */
@SpringBootApplication
@EnableSwagger2
//@MapperScan("org.siu.myboot.admin.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
