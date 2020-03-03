package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductSupplier Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:45:14
 * @Version 0.0.1
 */
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductSupplier> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductSupplier> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductSupplier> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductSupplier> findByUpateTimeAfter(Date upateTime);

}
