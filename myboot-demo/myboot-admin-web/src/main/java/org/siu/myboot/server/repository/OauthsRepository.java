package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Oauths Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-24 12:34:01
 * @Version 0.0.1
 */
public interface OauthsRepository extends JpaRepository<Oauths, Long> {

	/**
	 * createTime
	 *@param createTime
	 *@return
	 */
	List<Oauths> findByCreateTimeBefore(Date createTime);

	/**
	 * createTime
	 *@param createTime
	 *@return
	 */
	List<Oauths> findByCreateTimeAfter(Date createTime);
	/**
	 * updateTime
	 *@param updateTime
	 *@return
	 */
	List<Oauths> findByUpdateTimeBefore(Date updateTime);

	/**
	 * updateTime
	 *@param updateTime
	 *@return
	 */
	List<Oauths> findByUpdateTimeAfter(Date updateTime);
// findBy(

}
