package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductStock Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductStock> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductStock> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductStock> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductStock> findByUpateTimeAfter(Date upateTime);

}
