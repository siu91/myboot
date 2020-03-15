package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.datasource.jpa.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.datasource.jpa.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductCategory;
import org.siu.myboot.server.entity.po.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductCategory 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:47:03
 * @Version 0.0.1
 */
@Repository
public class ProductCategoryRepositoryQueryDsl extends BaseJpaRepository<ProductCategory, Long>  {

	public ProductCategoryRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductCategory.class, entityManager);
	}

	/**
	 * QProductCategory QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductCategory qProductCategory = QProductCategory.productCategory;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductCategory> queryExample(Pageable pageable) {
		JPAQuery<ProductCategory> countQuery = jpaQueryFactory.selectFrom(qProductCategory);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductCategory> queryPage(Pageable pageable, ProductCategory params) {
        JPAQuery<ProductCategory> countQuery = jpaQueryFactory.selectFrom(qProductCategory);
        QueryBuilder.buildCondition(countQuery, qProductCategory, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductCategory> queryList(ProductCategory params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductCategory> query = jpaQueryFactory.selectFrom(qProductCategory);
        QueryBuilder.buildCondition(query, qProductCategory, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
