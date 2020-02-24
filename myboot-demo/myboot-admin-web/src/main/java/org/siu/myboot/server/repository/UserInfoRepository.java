package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * UserInfo Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-24 12:34:01
 * @Version 0.0.1
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


	/**
	 * 用户名（唯一）
	 *@param userName
	 *@return
	 */
	UserInfo findByUserName(String userName);

	/**
	 * 手机（唯一）
	 *@param phone
	 *@return
	 */
	UserInfo findByPhone(String phone);
	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserInfo> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<UserInfo> findByCreateTimeAfter(Date createTime);
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
// findByUserNameOrPhone(String userName,String phone)

	/**
	 * findByUserNameOrPhone
	 *@return
	 */
	UserInfo findByUserNameOrPhone(String userName,String phone);

}
