package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserAddr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserAddr Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserAddrRepository extends JpaRepository<UserAddr, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserAddr> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserAddr> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<UserAddr> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<UserAddr> findByUpateTimeAfter(Date upateTime);

}
