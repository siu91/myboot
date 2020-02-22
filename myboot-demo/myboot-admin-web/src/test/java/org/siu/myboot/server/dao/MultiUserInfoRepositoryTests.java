package org.siu.myboot.server.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siu.myboot.server.daoprimary.UserInfoRepository1;
import org.siu.myboot.server.daosecondary.UserInfoRepository2;
import org.siu.myboot.server.model.po.UserInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author Siu
 * @Date 2020/2/18 9:58
 * @Version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiUserInfoRepositoryTests {

    @Resource
    private UserInfoRepository1 userInfoRepository1;
    @Resource
    private UserInfoRepository2 userInfoRepository2;


    @Test
    public void test() {
        testUserInfo1();
        testUserInfo2();
    }

    public void testUserInfo1() {

        UserInfo u1 = new UserInfo();
        u1.setUserName("user1");
        u1.setPhone("138");
        u1.setCreateTime(new Timestamp(new Date().getTime()));

        UserInfo u2 = new UserInfo();
        userInfoRepository1.save(u1);
        u2.setUserName("user2");
        u2.setPhone("139");
        u2.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository1.save(u2);

        UserInfo u3 = new UserInfo();
        u3.setUserName("user3");
        u3.setPhone("140");
        u3.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository1.save(u3);


        int count = userInfoRepository1.findAll().size();

        Assert.assertEquals(3, count);

        UserInfo user1 = userInfoRepository1.findByUserNameOrPhone("user1", "pp");

        Assert.assertEquals("user1", user1.getUserName());

        userInfoRepository1.delete(userInfoRepository1.findByUserName("user1"));


        List<UserInfo> users = userInfoRepository1.findByCreateTimeBefore(new Timestamp(new Date().getTime()));
        Assert.assertEquals(2, users.size());

        userInfoRepository1.delete(userInfoRepository1.findByUserName("user2"));
        userInfoRepository1.delete(userInfoRepository1.findByUserName("user3"));

    }

    public void testUserInfo2() {

        UserInfo u1 = new UserInfo();
        u1.setUserName("user1");
        u1.setPhone("138");
        u1.setCreateTime(new Timestamp(new Date().getTime()));

        UserInfo u2 = new UserInfo();
        userInfoRepository2.save(u1);
        u2.setUserName("user2");
        u2.setPhone("139");
        u2.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository2.save(u2);

        UserInfo u3 = new UserInfo();
        u3.setUserName("user3");
        u3.setPhone("140");
        u3.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository2.save(u3);


        int count = userInfoRepository2.findAll().size();

        Assert.assertEquals(3, count);

        UserInfo user1 = userInfoRepository2.findByUserNameOrPhone("user1", "pp");

        Assert.assertEquals("user1", user1.getUserName());

        userInfoRepository2.delete(userInfoRepository2.findByUserName("user1"));


        List<UserInfo> users = userInfoRepository2.findByCreateTimeBefore(new Timestamp(new Date().getTime()));
        Assert.assertEquals(2, users.size());

        userInfoRepository2.delete(userInfoRepository2.findByUserName("user2"));
        userInfoRepository2.delete(userInfoRepository2.findByUserName("user3"));

    }


}
