package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.datasource.jpa.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.datasource.jpa.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserPointLog;
import org.siu.myboot.server.entity.po.UserPointLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserPointLog 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserPointLogRepositoryQueryDsl extends BaseJpaRepository<UserPointLog, Long>  {

	public UserPointLogRepositoryQueryDsl(EntityManager entityManager) {
		super(UserPointLog.class, entityManager);
	}

	/**
	 * QUserPointLog QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserPointLog qUserPointLog = QUserPointLog.userPointLog;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserPointLog> queryExample(Pageable pageable) {
		JPAQuery<UserPointLog> countQuery = jpaQueryFactory.selectFrom(qUserPointLog);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserPointLog> queryPage(Pageable pageable, UserPointLog params) {
        JPAQuery<UserPointLog> countQuery = jpaQueryFactory.selectFrom(qUserPointLog);
        QueryBuilder.buildCondition(countQuery, qUserPointLog, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserPointLog> queryList(UserPointLog params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserPointLog> query = jpaQueryFactory.selectFrom(qUserPointLog);
        QueryBuilder.buildCondition(query, qUserPointLog, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
