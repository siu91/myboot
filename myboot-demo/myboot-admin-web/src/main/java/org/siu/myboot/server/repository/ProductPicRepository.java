package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductPic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductPic Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductPicRepository extends JpaRepository<ProductPic, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductPic> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductPic> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductPic> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductPic> findByUpateTimeAfter(Date upateTime);

}
