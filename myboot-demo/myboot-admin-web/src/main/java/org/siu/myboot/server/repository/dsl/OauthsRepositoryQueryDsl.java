package org.siu.myboot.server.repository.dsl;

import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;;
import org.siu.myboot.server.entity.po.QOauths;
import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Oauths 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-24 23:46:44
 * @Version 0.0.1
 */
@Repository
public class OauthsRepositoryQueryDsl extends BaseJpaRepository<Oauths, Long>  {

	public OauthsRepositoryQueryDsl(EntityManager entityManager) {
		super(Oauths.class, entityManager);
	}

	/**
	 * QOauths QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QOauths qOauths = QOauths.oauths;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<Oauths> queryExample(Pageable pageable) {
		JPAQuery countQuery = jpaQueryFactory.selectFrom(qOauths);
		return basePageQuery(countQuery, pageable);
	}
}
