package org.siu.myboot.server.repository;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siu.myboot.jooq.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author Siu
 * @Date 2020/2/18 9:58
 * @Version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqUserTests {

    @Autowired
    private DSLContext dsl;

    @Test
    public void test() {
        dsl.insertInto(User.USER,
                User.USER.USER_NAME, User.USER.PASSWORD, User.USER.PHONE, User.USER.VERSION, User.USER.DELETE_STATUS)
                .values("tet1", "ds", "dsd", 1L, (short) 0).execute();
        System.out.println("--------------");
    }

}
