package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserRole;
import org.siu.myboot.server.entity.po.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserRole 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@Repository
public class UserRoleRepositoryQueryDsl extends BaseJpaRepository<UserRole, Long>  {

	public UserRoleRepositoryQueryDsl(EntityManager entityManager) {
		super(UserRole.class, entityManager);
	}

	/**
	 * QUserRole QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserRole qUserRole = QUserRole.userRole;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserRole> queryExample(Pageable pageable) {
		JPAQuery<UserRole> countQuery = jpaQueryFactory.selectFrom(qUserRole);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserRole> queryPage(Pageable pageable, UserRole params) {
        JPAQuery<UserRole> countQuery = jpaQueryFactory.selectFrom(qUserRole);
        QueryBuilder.buildCondition(countQuery, qUserRole, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserRole> queryList(UserRole params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserRole> query = jpaQueryFactory.selectFrom(qUserRole);
        QueryBuilder.buildCondition(query, qUserRole, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
