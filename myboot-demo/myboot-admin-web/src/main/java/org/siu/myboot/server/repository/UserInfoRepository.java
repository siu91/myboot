package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserInfo Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	/**
	 * 注册时间
	 *@param registerTime 
	 *@return
	 */
	List<UserInfo> findByRegisterTime Before(Date registerTime );

	/**
	 * 注册时间
	 *@param registerTime 
	 *@return
	 */
	List<UserInfo> findByRegisterTime After(Date registerTime );
	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserInfo> findByUpdateTimeBefore(Date updateTime);

	/**
	 * 更新时间
	 *@param updateTime
	 *@return
	 */
	List<UserInfo> findByUpdateTimeAfter(Date updateTime);

}
