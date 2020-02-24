package org.siu.myboot.server.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.repository.dsl.UserInfoRepositoryQueryDsl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
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
    @Resource
    private OauthsRepository oauthsRepository;

    @Resource
    private UserInfoRepositoryQueryDsl userInfoRepositoryQueryDsl;


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


        List<UserInfo> users = userInfoRepository.findByCreateTimeBefore(new Date());
        Assert.assertEquals(2, users.size());

        userInfoRepository.delete(userInfoRepository.findByUserName("user2"));
        userInfoRepository.delete(userInfoRepository.findByUserName("user3"));

    }


    @Test
    public void testUserInfoRepositoryQueryDsl() {
        // 插入数据
        UserInfo u1 = new UserInfo();
        u1.setUserName("user1");
        u1.setPhone("138");
        u1.setCreateTime(new Timestamp(new Date().getTime()));

        UserInfo u2 = new UserInfo();
        userInfoRepositoryQueryDsl.save(u1);
        u2.setUserName("user2");
        u2.setPhone("139");
        u2.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepositoryQueryDsl.save(u2);

        UserInfo u3 = new UserInfo();
        u3.setUserName("user3");
        u3.setPhone("140");
        u3.setCreateTime(new Timestamp(new Date().getTime()));
        userInfoRepositoryQueryDsl.save(u3);

        // 查询所有
        List<UserInfo> users = userInfoRepositoryQueryDsl.findAll();
        for (UserInfo user : users) {
            Oauths oauths1 = new Oauths();
            oauths1.setUserId(user.getUserId());
            oauths1.setOauthId("OauthId1");
            oauths1.setUnionid("setUnionid");
            oauths1.setOauthType(0);
            oauths1.setCredential("Credential");
            oauthsRepository.save(oauths1);
            oauthsRepository.save(oauths1);
            oauthsRepository.save(oauths1);
        }

        Pageable pageable = QPageRequest.of(0, 10);
        Page<UserInfo> page = userInfoRepositoryQueryDsl.queryUserOauths(pageable);
        List<UserInfo> userInfoList = page.getContent();
        System.out.println(userInfoList.size());
    }
}
