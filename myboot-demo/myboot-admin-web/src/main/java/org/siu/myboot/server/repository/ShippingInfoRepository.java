package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ShippingInfo Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ShippingInfo> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ShippingInfo> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ShippingInfo> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ShippingInfo> findByUpateTimeAfter(Date upateTime);

}
