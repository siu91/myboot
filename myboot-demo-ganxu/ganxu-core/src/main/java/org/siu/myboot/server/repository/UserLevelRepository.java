package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserLevel Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserLevelRepository extends JpaRepository<UserLevel, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserLevel> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserLevel> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserLevel> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserLevel> findByUpdateTimeAfter(Date updateTime);

}
