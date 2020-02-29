package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Product Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Product> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Product> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Product> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Product> findByUpateTimeAfter(Date upateTime);

}
