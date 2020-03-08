package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * RoleAuthority Repository层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<RoleAuthority> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<RoleAuthority> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<RoleAuthority> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<RoleAuthority> findByUpdateTimeAfter(Date updateTime);

}
