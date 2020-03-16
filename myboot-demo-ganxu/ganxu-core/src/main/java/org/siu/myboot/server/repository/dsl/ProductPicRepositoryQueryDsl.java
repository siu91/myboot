package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.jpa.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.jpa.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductPic;
import org.siu.myboot.server.entity.po.ProductPic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductPic 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ProductPicRepositoryQueryDsl extends BaseJpaRepository<ProductPic, Long>  {

	public ProductPicRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductPic.class, entityManager);
	}

	/**
	 * QProductPic QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductPic qProductPic = QProductPic.productPic;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductPic> queryExample(Pageable pageable) {
		JPAQuery<ProductPic> countQuery = jpaQueryFactory.selectFrom(qProductPic);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductPic> queryPage(Pageable pageable, ProductPic params) {
        JPAQuery<ProductPic> countQuery = jpaQueryFactory.selectFrom(qProductPic);
        QueryBuilder.buildCondition(countQuery, qProductPic, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductPic> queryList(ProductPic params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductPic> query = jpaQueryFactory.selectFrom(qProductPic);
        QueryBuilder.buildCondition(query, qProductPic, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
