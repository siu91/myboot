package org.siu.myboot.server.daoprimary;

import org.siu.myboot.server.model.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Spring Data JPA 可以根据接口方法名来实现数据库操作
 * 主要的语法是
 * findXXBy
 * readAXXBy
 * queryXXBy
 * countXXBy
 * getXXBy
 *
 * @Author Siu
 * @Date 2020/2/20 11:41
 * @Version 0.0.1
 */
public interface UserInfoRepository1 extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);

    UserInfo findByUserNameOrPhone(String userName, String phone);

    List<UserInfo> findByCreateTimeBefore(Timestamp createTime);


}