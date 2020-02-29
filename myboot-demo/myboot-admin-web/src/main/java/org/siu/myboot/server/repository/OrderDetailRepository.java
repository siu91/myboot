package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * OrderDetail Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<OrderDetail> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<OrderDetail> findByUpateTimeAfter(Date upateTime);

}
