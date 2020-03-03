package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserAddr;
import org.siu.myboot.server.entity.po.UserAddr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserAddr 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserAddrRepositoryQueryDsl extends BaseJpaRepository<UserAddr, Long>  {

	public UserAddrRepositoryQueryDsl(EntityManager entityManager) {
		super(UserAddr.class, entityManager);
	}

	/**
	 * QUserAddr QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QUserAddr qUserAddr = QUserAddr.userAddr;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<UserAddr> queryExample(Pageable pageable) {
		JPAQuery<UserAddr> countQuery = jpaQueryFactory.selectFrom(qUserAddr);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserAddr> queryPage(Pageable pageable, UserAddr params) {
        JPAQuery<UserAddr> countQuery = jpaQueryFactory.selectFrom(qUserAddr);
        QueryBuilder.buildCondition(countQuery, qUserAddr, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserAddr> queryList(UserAddr params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserAddr> query = jpaQueryFactory.selectFrom(qUserAddr);
        QueryBuilder.buildCondition(query, qUserAddr, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
