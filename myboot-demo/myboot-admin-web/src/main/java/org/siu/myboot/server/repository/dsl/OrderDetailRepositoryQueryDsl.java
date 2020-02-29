package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QOrderDetail;
import org.siu.myboot.server.entity.po.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * OrderDetail 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class OrderDetailRepositoryQueryDsl extends BaseJpaRepository<OrderDetail, Long>  {

	public OrderDetailRepositoryQueryDsl(EntityManager entityManager) {
		super(OrderDetail.class, entityManager);
	}

	/**
	 * QOrderDetail QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QOrderDetail qOrderDetail = QOrderDetail.orderDetail;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<OrderDetail> queryExample(Pageable pageable) {
		JPAQuery<OrderDetail> countQuery = jpaQueryFactory.selectFrom(qOrderDetail);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<OrderDetail> queryPage(Pageable pageable, OrderDetail params) {
        JPAQuery<OrderDetail> countQuery = jpaQueryFactory.selectFrom(qOrderDetail);
        QueryBuilder.buildCondition(countQuery, qOrderDetail, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<OrderDetail> queryList(OrderDetail params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<OrderDetail> query = jpaQueryFactory.selectFrom(qOrderDetail);
        QueryBuilder.buildCondition(query, qOrderDetail, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
