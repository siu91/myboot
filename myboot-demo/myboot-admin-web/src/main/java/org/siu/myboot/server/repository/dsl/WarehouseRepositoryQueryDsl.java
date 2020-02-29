package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QWarehouse;
import org.siu.myboot.server.entity.po.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * Warehouse 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class WarehouseRepositoryQueryDsl extends BaseJpaRepository<Warehouse, Long>  {

	public WarehouseRepositoryQueryDsl(EntityManager entityManager) {
		super(Warehouse.class, entityManager);
	}

	/**
	 * QWarehouse QueryDSL Object: Use jpaQueryFactory build JPAQuery
	 */
	private static final QWarehouse qWarehouse = QWarehouse.warehouse;

	/**
	 * queryExample
	 *
	 * @param pageable
	 * @return
	 */
	public Page<Warehouse> queryExample(Pageable pageable) {
		JPAQuery<Warehouse> countQuery = jpaQueryFactory.selectFrom(qWarehouse);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<Warehouse> queryPage(Pageable pageable, Warehouse params) {
        JPAQuery<Warehouse> countQuery = jpaQueryFactory.selectFrom(qWarehouse);
        QueryBuilder.buildCondition(countQuery, qWarehouse, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<Warehouse> queryList(Warehouse params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<Warehouse> query = jpaQueryFactory.selectFrom(qWarehouse);
        QueryBuilder.buildCondition(query, qWarehouse, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
