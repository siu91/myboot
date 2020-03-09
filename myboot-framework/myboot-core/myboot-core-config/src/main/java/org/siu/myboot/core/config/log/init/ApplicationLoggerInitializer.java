/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package org.siu.myboot.core.config.log.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;

/**
 * @author Siu
 * <p>
 * 通过环境变量的形式注入 logging.file
 * 自动维护 Spring Boot Admin Logger Viewer
 *
 * 2.2 中这样配置无效果
 */
@Slf4j
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        String appName = environment.getProperty("spring.application.name");

        String logBase = environment.getProperty("LOGGING_PATH", "logs");


        String userDir = environment.getProperty("user.dir");
        String file = userDir + File.separator + logBase + File.separator + appName + File.separator + "debug.log";

        // spring boot admin 直接加载日志
       // System.setProperty("logging.file", file);
        //System.setProperty("logging.file.name", file);
        //System.setProperty("logging.file.name", "logs/test.log");
        //System.setProperty("logging.file.path", file);
        // System.setProperty("logging.file", file);
    }
}
