package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserLoginLog Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserLoginLogRepository extends JpaRepository<UserLoginLog, Long> {

	/**
	 * 登录时间
	 *@param loginTime
	 *@return
	 */
	List<UserLoginLog> findByLoginTimeBefore(Date loginTime);

	/**
	 * 登录时间
	 *@param loginTime
	 *@return
	 */
	List<UserLoginLog> findByLoginTimeAfter(Date loginTime);

}
