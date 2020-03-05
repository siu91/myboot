package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QRoleAuthority;
import org.siu.myboot.server.entity.po.RoleAuthority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * RoleAuthority 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@Repository
public class RoleAuthorityRepositoryQueryDsl extends BaseJpaRepository<RoleAuthority, Long>  {

	public RoleAuthorityRepositoryQueryDsl(EntityManager entityManager) {
		super(RoleAuthority.class, entityManager);
	}

	/**
	 * QRoleAuthority QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QRoleAuthority qRoleAuthority = QRoleAuthority.roleAuthority;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<RoleAuthority> queryExample(Pageable pageable) {
		JPAQuery<RoleAuthority> countQuery = jpaQueryFactory.selectFrom(qRoleAuthority);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<RoleAuthority> queryPage(Pageable pageable, RoleAuthority params) {
        JPAQuery<RoleAuthority> countQuery = jpaQueryFactory.selectFrom(qRoleAuthority);
        QueryBuilder.buildCondition(countQuery, qRoleAuthority, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<RoleAuthority> queryList(RoleAuthority params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<RoleAuthority> query = jpaQueryFactory.selectFrom(qRoleAuthority);
        QueryBuilder.buildCondition(query, qRoleAuthority, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
