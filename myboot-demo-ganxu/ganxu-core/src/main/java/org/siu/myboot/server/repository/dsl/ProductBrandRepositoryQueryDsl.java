package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.datasource.jpa.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.datasource.jpa.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductBrand;
import org.siu.myboot.server.entity.po.ProductBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductBrand 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:43:44
 * @Version 0.0.1
 */
@Repository
public class ProductBrandRepositoryQueryDsl extends BaseJpaRepository<ProductBrand, Long>  {

	public ProductBrandRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductBrand.class, entityManager);
	}

	/**
	 * QProductBrand QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductBrand qProductBrand = QProductBrand.productBrand;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductBrand> queryExample(Pageable pageable) {
		JPAQuery<ProductBrand> countQuery = jpaQueryFactory.selectFrom(qProductBrand);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductBrand> queryPage(Pageable pageable, ProductBrand params) {
        JPAQuery<ProductBrand> countQuery = jpaQueryFactory.selectFrom(qProductBrand);
        QueryBuilder.buildCondition(countQuery, qProductBrand, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductBrand> queryList(ProductBrand params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductBrand> query = jpaQueryFactory.selectFrom(qProductBrand);
        QueryBuilder.buildCondition(query, qProductBrand, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
