package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * ProductComment Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductComment> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<ProductComment> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductComment> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<ProductComment> findByUpateTimeAfter(Date upateTime);

}
