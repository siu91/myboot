package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * OrderCart Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<OrderCart> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<OrderCart> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<OrderCart> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<OrderCart> findByUpateTimeAfter(Date upateTime);

}
