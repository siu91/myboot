package org.siu.myboot.admin.dao;

import org.siu.myboot.admin.model.po.User;
import org.siu.myboot.admin.model.qo.UserParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Siu
 * @Date 2020/2/18 9:58
 * @Version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testUser() {
        // 增加
        userMapper.insert(new User().setName("Siu").setAge(29).setPass("dfasdfds"));
        userMapper.insert(new User().setName("Siu").setAge(29).setPass("dfasdfds"));
        userMapper.insert(new User().setName("Siu").setAge(29).setPass("dfasdfds"));
        // 查询数量
        int count = userMapper.getCount(new UserParam().setName("Siu"));
        Assert.assertEquals(3, count);
        // 查询所有
        List<User> users = userMapper.getAll();
        // 更新所有
        for (User user : users) {
            // 查询是否存在
            User temp = userMapper.getOne(user.getId());
            Assert.assertNotNull(temp);
            user.setName("Jasmine");
            user.setPass("aaaaaaaaaaaa");
            userMapper.update(user);
        }
        // 根据ID 删除所有
        for (User user : users) {
            userMapper.delete(user.getId());
        }

    }


}
