package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * UserInfo Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);

    UserInfo findByUserNameOrPhone(String userName, String phone);

    List<UserInfo> findByCreateTimeBefore(Timestamp createTime);


}
