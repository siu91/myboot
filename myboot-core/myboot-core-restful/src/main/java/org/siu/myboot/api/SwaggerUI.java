/*
 * @(#) CecProjectController.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package org.siu.myboot.api;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author Siu
 * @Date 2020/2/16 13:46
 * @Version 0.0.1
 */
@Controller
@RequestMapping("")
@Slf4j
@Profile({"dev", "test"})
@Api(hidden = true)
public class SwaggerUI {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    void swagger(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

}
