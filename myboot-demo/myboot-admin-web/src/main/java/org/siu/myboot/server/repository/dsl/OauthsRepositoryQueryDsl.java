package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QOauths;
import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * Oauths 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-27 20:33:51
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
		JPAQuery<Oauths> countQuery = jpaQueryFactory.selectFrom(qOauths);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<Oauths> queryPage(Pageable pageable, Oauths params) {
        JPAQuery<Oauths> countQuery = jpaQueryFactory.selectFrom(qOauths);
        QueryBuilder.buildCondition(countQuery, qOauths, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<Oauths> queryList(Oauths params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<Oauths> query = jpaQueryFactory.selectFrom(qOauths);
        QueryBuilder.buildCondition(query, qOauths, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
