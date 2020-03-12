package org.siu.myboot.core.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Siu
 * @Date 2020/3/12 20:14
 * @Version 0.0.1
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * 加密工具类
     *
     * @return
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
