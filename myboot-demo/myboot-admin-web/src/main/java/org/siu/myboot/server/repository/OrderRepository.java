package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Order Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:30:49
 * @Version 0.0.1
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Order> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Order> findByCreateTimeAfter(Date createTime);
	/**
	 * 发货时间
	 *@param shippingTime
	 *@return
	 */
	List<Order> findByShippingTimeBefore(Date shippingTime);

	/**
	 * 发货时间
	 *@param shippingTime
	 *@return
	 */
	List<Order> findByShippingTimeAfter(Date shippingTime);
	/**
	 * 支付时间
	 *@param payTime
	 *@return
	 */
	List<Order> findByPayTimeBefore(Date payTime);

	/**
	 * 支付时间
	 *@param payTime
	 *@return
	 */
	List<Order> findByPayTimeAfter(Date payTime);
	/**
	 * 收货时间
	 *@param receiveTime
	 *@return
	 */
	List<Order> findByReceiveTimeBefore(Date receiveTime);

	/**
	 * 收货时间
	 *@param receiveTime
	 *@return
	 */
	List<Order> findByReceiveTimeAfter(Date receiveTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Order> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Order> findByUpateTimeAfter(Date upateTime);

}
