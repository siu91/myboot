package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductBrand Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductBrand> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductBrand> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductBrand> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductBrand> findByUpateTimeAfter(Date upateTime);

}
