package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QOrder;
import org.siu.myboot.server.entity.po.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * Order 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:30:49
 * @Version 0.0.1
 */
@Repository
public class OrderRepositoryQueryDsl extends BaseJpaRepository<Order, Long>  {

	public OrderRepositoryQueryDsl(EntityManager entityManager) {
		super(Order.class, entityManager);
	}

	/**
	 * QOrder QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QOrder qOrder = QOrder.order;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<Order> queryExample(Pageable pageable) {
		JPAQuery<Order> countQuery = jpaQueryFactory.selectFrom(qOrder);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<Order> queryPage(Pageable pageable, Order params) {
        JPAQuery<Order> countQuery = jpaQueryFactory.selectFrom(qOrder);
        QueryBuilder.buildCondition(countQuery, qOrder, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<Order> queryList(Order params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<Order> query = jpaQueryFactory.selectFrom(qOrder);
        QueryBuilder.buildCondition(query, qOrder, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
