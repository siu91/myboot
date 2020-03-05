package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Role Repository层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * 通过角色名称查找
	 *
	 * @param roleName
	 * @return
	 */
	Role findByRoleName(String roleName);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Role> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Role> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<Role> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<Role> findByUpdateTimeAfter(Date updateTime);

}
