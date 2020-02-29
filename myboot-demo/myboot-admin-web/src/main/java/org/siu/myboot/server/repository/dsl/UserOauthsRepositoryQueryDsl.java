package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserOauths;
import org.siu.myboot.server.entity.po.UserOauths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserOauths 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserOauthsRepositoryQueryDsl extends BaseJpaRepository<UserOauths, Long>  {

	public UserOauthsRepositoryQueryDsl(EntityManager entityManager) {
		super(UserOauths.class, entityManager);
	}

	/**
	 * QUserOauths QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserOauths qUserOauths = QUserOauths.userOauths;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserOauths> queryExample(Pageable pageable) {
		JPAQuery<UserOauths> countQuery = jpaQueryFactory.selectFrom(qUserOauths);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserOauths> queryPage(Pageable pageable, UserOauths params) {
        JPAQuery<UserOauths> countQuery = jpaQueryFactory.selectFrom(qUserOauths);
        QueryBuilder.buildCondition(countQuery, qUserOauths, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserOauths> queryList(UserOauths params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserOauths> query = jpaQueryFactory.selectFrom(qUserOauths);
        QueryBuilder.buildCondition(query, qUserOauths, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
