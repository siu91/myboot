package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserOauths;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserOauths Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserOauthsRepository extends JpaRepository<UserOauths, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserOauths> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserOauths> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserOauths> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserOauths> findByUpdateTimeAfter(Date updateTime);

}
