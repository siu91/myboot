package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QProductComment;
import org.siu.myboot.server.entity.po.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * ProductComment 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class ProductCommentRepositoryQueryDsl extends BaseJpaRepository<ProductComment, Long>  {

	public ProductCommentRepositoryQueryDsl(EntityManager entityManager) {
		super(ProductComment.class, entityManager);
	}

	/**
	 * QProductComment QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QProductComment qProductComment = QProductComment.productComment;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<ProductComment> queryExample(Pageable pageable) {
		JPAQuery<ProductComment> countQuery = jpaQueryFactory.selectFrom(qProductComment);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<ProductComment> queryPage(Pageable pageable, ProductComment params) {
        JPAQuery<ProductComment> countQuery = jpaQueryFactory.selectFrom(qProductComment);
        QueryBuilder.buildCondition(countQuery, qProductComment, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<ProductComment> queryList(ProductComment params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<ProductComment> query = jpaQueryFactory.selectFrom(qProductComment);
        QueryBuilder.buildCondition(query, qProductComment, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
