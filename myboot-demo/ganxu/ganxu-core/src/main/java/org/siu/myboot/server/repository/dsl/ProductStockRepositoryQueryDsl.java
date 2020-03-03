package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductStock;
import org.siu.myboot.server.entity.po.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductStock 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ProductStockRepositoryQueryDsl extends BaseJpaRepository<ProductStock, Long>  {

	public ProductStockRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductStock.class, entityManager);
	}

	/**
	 * QProductStock QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductStock qProductStock = QProductStock.productStock;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductStock> queryExample(Pageable pageable) {
		JPAQuery<ProductStock> countQuery = jpaQueryFactory.selectFrom(qProductStock);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductStock> queryPage(Pageable pageable, ProductStock params) {
        JPAQuery<ProductStock> countQuery = jpaQueryFactory.selectFrom(qProductStock);
        QueryBuilder.buildCondition(countQuery, qProductStock, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductStock> queryList(ProductStock params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductStock> query = jpaQueryFactory.selectFrom(qProductStock);
        QueryBuilder.buildCondition(query, qProductStock, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
