package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Oauths Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-27 16:10:42
 * @Version 0.0.1
 */
public interface OauthsRepository extends JpaRepository<Oauths, Long> {

	/**
	 * (qp)
	 *@param createTime
	 *@return
	 */
	List<Oauths> findByCreateTimeBefore(Date createTime);

	/**
	 * (qp)
	 *@param createTime
	 *@return
	 */
	List<Oauths> findByCreateTimeAfter(Date createTime);
	/**
	 * (qp)
	 *@param updateTime
	 *@return
	 */
	List<Oauths> findByUpdateTimeBefore(Date updateTime);

	/**
	 * (qp)
	 *@param updateTime
	 *@return
	 */
	List<Oauths> findByUpdateTimeAfter(Date updateTime);

}
