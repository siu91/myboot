package org.siu.myboot.server.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.siu.myboot.core.datasource.dds.DataSourceId;
import org.siu.myboot.core.datasource.dds.DynamicDataSourceHolder;
import org.siu.myboot.server.entity.po.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author Siu
 * @Date 2020/2/18 9:58
 * @Version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;


    /**
     * 测试数据源切换
     */
    @Test
    public void test() {
        //
        User u1 = new User();
        u1.setPassword("122");
        u1.setVersion(2L);
        u1.setDeleteStatus(1);
        u1.setPhone("1424");
        u1.setUserName("u1");

        userRepository.save(u1);

        // 切换数据源
        DynamicDataSourceHolder.select(DataSourceId.SECONDARY);
        User u2 = new User();
        u2.setPassword("122");
        u2.setVersion(2L);
        u2.setDeleteStatus(1);
        u2.setPhone("1424");
        u2.setUserName("u2");
        userRepository.save(u2);

        System.out.println("--------");

    }

    /**
     * 测试数据源切换
     */
    @Test
    public void test2() {

        // 切换数据源
        DynamicDataSourceHolder.select(DataSourceId.SECONDARY);
        User u2 = new User();
        u2.setPassword("122");
        u2.setVersion(2L);
        u2.setDeleteStatus(1);
        u2.setPhone("1424");
        u2.setUserName("u2");
        userRepository.save(u2);

        System.out.println("--------");

    }

}
