package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserLevel;
import org.siu.myboot.server.entity.po.UserLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserLevel 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserLevelRepositoryQueryDsl extends BaseJpaRepository<UserLevel, Long>  {

	public UserLevelRepositoryQueryDsl(EntityManager entityManager) {
		super(UserLevel.class, entityManager);
	}

	/**
	 * QUserLevel QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserLevel qUserLevel = QUserLevel.userLevel;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserLevel> queryExample(Pageable pageable) {
		JPAQuery<UserLevel> countQuery = jpaQueryFactory.selectFrom(qUserLevel);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserLevel> queryPage(Pageable pageable, UserLevel params) {
        JPAQuery<UserLevel> countQuery = jpaQueryFactory.selectFrom(qUserLevel);
        QueryBuilder.buildCondition(countQuery, qUserLevel, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserLevel> queryList(UserLevel params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserLevel> query = jpaQueryFactory.selectFrom(qUserLevel);
        QueryBuilder.buildCondition(query, qUserLevel, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
