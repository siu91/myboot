package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductCategory Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductCategory> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductCategory> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductCategory> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductCategory> findByUpateTimeAfter(Date upateTime);

}
