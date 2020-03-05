package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserRole Repository层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserRole> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserRole> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserRole> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserRole> findByUpdateTimeAfter(Date updateTime);

}
