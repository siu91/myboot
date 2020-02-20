package org.siu.myboot.admin.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * 单元测试示例
 *
 * @Author Siu
 * @Date 2020/2/16 13:00
 * @Version 0.0.1
 */
@SpringBootTest
public class DemoControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=小明")
                .accept(MediaType.APPLICATION_JSON_UTF8))/*.andDo(print())*/
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("小明")));
    }

    @Test
    public void getUser() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/getUser"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result : "+responseString);
    }

    @Test
    public void getUserName() throws Exception {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/get/UserName1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result : "+responseString);
    }

    @Test
    public void saveUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                .param("name","")
                .param("age","666")
                .param("pass","test")
        );
    }
}
