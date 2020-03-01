package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * User Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 更新时间
     *
     * @param updateTime
     * @return
     */
    List<User> findByUpdateTimeBefore(Date updateTime);

    /**
     * 更新时间
     *
     * @param updateTime
     * @return
     */
    List<User> findByUpdateTimeAfter(Date updateTime);


    /**
     * @param userName
     * @param phone
     * @return
     */
    User findByUserNameOrPhone(String userName, String phone);

}
