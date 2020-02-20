package org.siu.myboot.admin.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siu.myboot.admin.model.po.UserInfo;
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
public class UserInfoRepositoryTests {

    @Resource
    private UserInfoRepository userInfoRepository;


    @Test
    public void testUserInfo() {

        UserInfo u1 = new UserInfo();
        u1.setUserName("user1");
        u1.setPhone("138");
        u1.setCreateTime(new Timestamp(new Date().getTime()));

        UserInfo u2 = new UserInfo();
        userInfoRepository.save(u1);
        u2.setUserName("user2");
        u2.setPhone("139");
        u2.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository.save(u2);

        UserInfo u3 = new UserInfo();
        u3.setUserName("user3");
        u3.setPhone("140");
        u3.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepository.save(u3);


        int count = userInfoRepository.findAll().size();

        Assert.assertEquals(3, count);

        UserInfo user1 = userInfoRepository.findByUserNameOrPhone("user1", "pp");

        Assert.assertEquals("user1", user1.getUserName());

        userInfoRepository.delete(userInfoRepository.findByUserName("user1"));


        List<UserInfo> users = userInfoRepository.findByCreateTimeBefore(new Timestamp(new Date().getTime()));
        Assert.assertEquals(2, users.size());

        userInfoRepository.delete(userInfoRepository.findByUserName("user2"));
        userInfoRepository.delete(userInfoRepository.findByUserName("user3"));

    }


}
