package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserLoginLog;
import org.siu.myboot.server.entity.po.UserLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserLoginLog 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserLoginLogRepositoryQueryDsl extends BaseJpaRepository<UserLoginLog, Long>  {

	public UserLoginLogRepositoryQueryDsl(EntityManager entityManager) {
		super(UserLoginLog.class, entityManager);
	}

	/**
	 * QUserLoginLog QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserLoginLog qUserLoginLog = QUserLoginLog.userLoginLog;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserLoginLog> queryExample(Pageable pageable) {
		JPAQuery<UserLoginLog> countQuery = jpaQueryFactory.selectFrom(qUserLoginLog);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserLoginLog> queryPage(Pageable pageable, UserLoginLog params) {
        JPAQuery<UserLoginLog> countQuery = jpaQueryFactory.selectFrom(qUserLoginLog);
        QueryBuilder.buildCondition(countQuery, qUserLoginLog, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserLoginLog> queryList(UserLoginLog params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserLoginLog> query = jpaQueryFactory.selectFrom(qUserLoginLog);
        QueryBuilder.buildCondition(query, qUserLoginLog, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
