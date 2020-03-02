package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProduct;
import org.siu.myboot.server.entity.po.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * Product 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ProductRepositoryQueryDsl extends BaseJpaRepository<Product, Long>  {

	public ProductRepositoryQueryDsl(EntityManager entityManager) {
		super(Product.class, entityManager);
	}

	/**
	 * QProduct QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProduct qProduct = QProduct.product;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<Product> queryExample(Pageable pageable) {
		JPAQuery<Product> countQuery = jpaQueryFactory.selectFrom(qProduct);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<Product> queryPage(Pageable pageable, Product params) {
        JPAQuery<Product> countQuery = jpaQueryFactory.selectFrom(qProduct);
        QueryBuilder.buildCondition(countQuery, qProduct, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<Product> queryList(Product params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<Product> query = jpaQueryFactory.selectFrom(qProduct);
        QueryBuilder.buildCondition(query, qProduct, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
