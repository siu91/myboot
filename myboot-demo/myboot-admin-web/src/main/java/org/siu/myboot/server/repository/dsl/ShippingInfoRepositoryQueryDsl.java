package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QShippingInfo;
import org.siu.myboot.server.entity.po.ShippingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ShippingInfo 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ShippingInfoRepositoryQueryDsl extends BaseJpaRepository<ShippingInfo, Long>  {

	public ShippingInfoRepositoryQueryDsl(EntityManager entityManager) {
		super(ShippingInfo.class, entityManager);
	}

	/**
	 * QShippingInfo QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QShippingInfo qShippingInfo = QShippingInfo.shippingInfo;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ShippingInfo> queryExample(Pageable pageable) {
		JPAQuery<ShippingInfo> countQuery = jpaQueryFactory.selectFrom(qShippingInfo);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ShippingInfo> queryPage(Pageable pageable, ShippingInfo params) {
        JPAQuery<ShippingInfo> countQuery = jpaQueryFactory.selectFrom(qShippingInfo);
        QueryBuilder.buildCondition(countQuery, qShippingInfo, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ShippingInfo> queryList(ShippingInfo params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ShippingInfo> query = jpaQueryFactory.selectFrom(qShippingInfo);
        QueryBuilder.buildCondition(query, qShippingInfo, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
