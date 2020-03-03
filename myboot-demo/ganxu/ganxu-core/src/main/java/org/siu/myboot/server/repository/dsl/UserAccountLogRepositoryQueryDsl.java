package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserAccountLog;
import org.siu.myboot.server.entity.po.UserAccountLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserAccountLog 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserAccountLogRepositoryQueryDsl extends BaseJpaRepository<UserAccountLog, Long>  {

	public UserAccountLogRepositoryQueryDsl(EntityManager entityManager) {
		super(UserAccountLog.class, entityManager);
	}

	/**
	 * QUserAccountLog QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserAccountLog qUserAccountLog = QUserAccountLog.userAccountLog;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserAccountLog> queryExample(Pageable pageable) {
		JPAQuery<UserAccountLog> countQuery = jpaQueryFactory.selectFrom(qUserAccountLog);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserAccountLog> queryPage(Pageable pageable, UserAccountLog params) {
        JPAQuery<UserAccountLog> countQuery = jpaQueryFactory.selectFrom(qUserAccountLog);
        QueryBuilder.buildCondition(countQuery, qUserAccountLog, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserAccountLog> queryList(UserAccountLog params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserAccountLog> query = jpaQueryFactory.selectFrom(qUserAccountLog);
        QueryBuilder.buildCondition(query, qUserAccountLog, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
