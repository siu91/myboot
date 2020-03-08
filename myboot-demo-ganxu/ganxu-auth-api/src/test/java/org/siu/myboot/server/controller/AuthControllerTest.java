package org.siu.myboot.server.controller;

import com.alibaba.fastjson.JSON;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.siu.myboot.core.constant.Constant;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.exception.AuthenticateFail;
import org.siu.myboot.core.exception.BaseException;
import org.siu.myboot.server.entity.qo.Login;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Siu
 * @Date 2020/3/8 15:16
 * @Version 0.0.1
 */
public class AuthControllerTest extends AbstractMockMvcTest {


    private static String token = null;


    @Before
    public void testAuth() throws Exception {
        /**
         *  测试登录换取token
         *
         *   "username": "string",
         *   "password": "admin12345"
         */

        String mvcResult = this.getMockMvc().perform(MockMvcRequestBuilders.post("/v1/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\": \"admin12345\", \"username\": \"string\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bearer "))).andReturn().getResponse().getContentAsString();

        Result result = JSON.parseObject(mvcResult, Result.class);
        token = (String) result.getData();

        System.out.println("Result:\n" + mvcResult);


    }


    @Test
    public void testChangePassword1() throws Exception {


        /**
         * 使用正确的token，修改其它用户的密码
         * 预期：返回【当前用户不匹配】
         */
        this.getExpectedException().expect(NestedServletException.class);
        //this.getExpectedException().expect(BaseException.class);
        //this.getExpectedException().expect(AuthenticateFail.class);
        this.getExpectedException().expectMessage(containsString("当前用户不匹配"));
        String mvcResult = this.getMockMvc().perform(MockMvcRequestBuilders.post("/v1/api/password")
                .header(Constant.Auth.AUTHORIZATION_HEADER, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPassword\": \"admin123456\",\"password\": \"admin12345\", \"username\": \"aaaaaa\"}"))
                .andReturn().getResponse().getContentAsString();


        System.out.println("Result:\n" + mvcResult);


    }

    @Test
    public void testChangePassword2() throws Exception {


        /**
         * 使用正确的token，修改自己的密码，原密码填错
         * 预期：返回 【用户名或密码错误】
         */
        this.getExpectedException().expect(NestedServletException.class);
        //this.getExpectedException().expect(BaseException.class);
        //this.getExpectedException().expect(AuthenticateFail.class);
        this.getExpectedException().expectMessage(containsString("用户名或密码错误"));
       String  mvcResult = this.getMockMvc().perform(MockMvcRequestBuilders.post("/v1/api/password")
                .header(Constant.Auth.AUTHORIZATION_HEADER, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"newPassword\": \"admin123456\",\"password\": \"admin1234599999999\", \"username\": \"string\"}"))
                .andReturn().getResponse().getContentAsString();


        System.out.println("Result:\n" + mvcResult);

    }


}
