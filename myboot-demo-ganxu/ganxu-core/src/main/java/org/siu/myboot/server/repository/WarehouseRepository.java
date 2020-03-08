package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Warehouse Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Warehouse> findByCreateTimeBefore(Date createTime);

	/**
	 * 创建时间
	 *@param createTime
	 *@return
	 */
	List<Warehouse> findByCreateTimeAfter(Date createTime);
	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Warehouse> findByUpateTimeBefore(Date upateTime);

	/**
	 * 更新时间
	 *@param upateTime
	 *@return
	 */
	List<Warehouse> findByUpateTimeAfter(Date upateTime);

}
