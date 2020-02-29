package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QOrderCart;
import org.siu.myboot.server.entity.po.OrderCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * OrderCart 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class OrderCartRepositoryQueryDsl extends BaseJpaRepository<OrderCart, Long>  {

	public OrderCartRepositoryQueryDsl(EntityManager entityManager) {
		super(OrderCart.class, entityManager);
	}

	/**
	 * QOrderCart QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QOrderCart qOrderCart = QOrderCart.orderCart;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<OrderCart> queryExample(Pageable pageable) {
		JPAQuery<OrderCart> countQuery = jpaQueryFactory.selectFrom(qOrderCart);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<OrderCart> queryPage(Pageable pageable, OrderCart params) {
        JPAQuery<OrderCart> countQuery = jpaQueryFactory.selectFrom(qOrderCart);
        QueryBuilder.buildCondition(countQuery, qOrderCart, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<OrderCart> queryList(OrderCart params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<OrderCart> query = jpaQueryFactory.selectFrom(qOrderCart);
        QueryBuilder.buildCondition(query, qOrderCart, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
