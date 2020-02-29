package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductSupplier;
import org.siu.myboot.server.entity.po.ProductSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductSupplier 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ProductSupplierRepositoryQueryDsl extends BaseJpaRepository<ProductSupplier, Integer>  {

	public ProductSupplierRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductSupplier.class, entityManager);
	}

	/**
	 * QProductSupplier QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductSupplier qProductSupplier = QProductSupplier.productSupplier;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductSupplier> queryExample(Pageable pageable) {
		JPAQuery<ProductSupplier> countQuery = jpaQueryFactory.selectFrom(qProductSupplier);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductSupplier> queryPage(Pageable pageable, ProductSupplier params) {
        JPAQuery<ProductSupplier> countQuery = jpaQueryFactory.selectFrom(qProductSupplier);
        QueryBuilder.buildCondition(countQuery, qProductSupplier, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductSupplier> queryList(ProductSupplier params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductSupplier> query = jpaQueryFactory.selectFrom(qProductSupplier);
        QueryBuilder.buildCondition(query, qProductSupplier, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
