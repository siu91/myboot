package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QRole;
import org.siu.myboot.server.entity.po.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * Role 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@Repository
public class RoleRepositoryQueryDsl extends BaseJpaRepository<Role, Long>  {

	public RoleRepositoryQueryDsl(EntityManager entityManager) {
		super(Role.class, entityManager);
	}

	/**
	 * QRole QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QRole qRole = QRole.role;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<Role> queryExample(Pageable pageable) {
		JPAQuery<Role> countQuery = jpaQueryFactory.selectFrom(qRole);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<Role> queryPage(Pageable pageable, Role params) {
        JPAQuery<Role> countQuery = jpaQueryFactory.selectFrom(qRole);
        QueryBuilder.buildCondition(countQuery, qRole, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<Role> queryList(Role params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<Role> query = jpaQueryFactory.selectFrom(qRole);
        QueryBuilder.buildCondition(query, qRole, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
