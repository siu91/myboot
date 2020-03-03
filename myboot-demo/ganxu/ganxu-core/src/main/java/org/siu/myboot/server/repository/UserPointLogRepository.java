package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserPointLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserPointLog Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserPointLogRepository extends JpaRepository<UserPointLog, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserPointLog> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserPointLog> findByCreateTimeAfter(Date createTime);

}
