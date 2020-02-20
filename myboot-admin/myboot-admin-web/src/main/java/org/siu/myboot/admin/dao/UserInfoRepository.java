package org.siu.myboot.admin.dao;

import org.siu.myboot.admin.model.po.UserInfo;
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
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);

    UserInfo findByUserNameOrPhone(String userName, String phone);

    List<UserInfo> findByCreateTimeBefore(Timestamp createTime);


}