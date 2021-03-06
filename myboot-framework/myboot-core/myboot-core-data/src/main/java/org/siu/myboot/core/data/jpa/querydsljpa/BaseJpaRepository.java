package org.siu.myboot.core.data.jpa.querydsljpa;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * BaseJpaRepository （JPA + QueryDSL）
 *
 * @Author Siu
 * @Date 2020/2/22 16:48
 * @Version 0.0.1
 */
public class BaseJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> implements QueryByExampleExecutor<T> {

    protected final JPAQueryFactory jpaQueryFactory;
    protected final QuerydslJpaPredicateExecutor<T> jpaPredicateExecutor;
    protected final EntityManager em;
    private final EntityPath<T> path;
    protected final Querydsl querydsl;

    public BaseJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.jpaPredicateExecutor = new QuerydslJpaPredicateExecutor<>(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em, SimpleEntityPathResolver.INSTANCE, getRepositoryMethodMetadata());
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.path = SimpleEntityPathResolver.INSTANCE.createPath(domainClass);
        this.querydsl = new Querydsl(em, new PathBuilder<T>(path.getType(), path.getMetadata()));
    }


    /**
     * 基础分页查询
     *
     * @param pageable 分页对象
     * @return
     */
    protected Page<T> basePageQuery(Pageable pageable) {
        return this.basePageQuery(jpaQueryFactory.selectFrom(path), pageable);

    }


    /**
     * 基础分页查询
     *
     * @param countQuery count查询对象（可能包括完整的sql）
     * @param pageable   分页对象
     * @return
     */
    protected Page<T> basePageQuery(JPAQuery<T> countQuery, Pageable pageable) {
        return this.basePageQuery(countQuery, pageable, null);

    }


    /**
     * 基础分页查询
     *
     * @param countQuery count查询对象（可能包括完整的sql）
     * @param pageable   分页对象
     * @return
     */
    protected Page<T> basePageQuery(JPAQuery<T> countQuery, Pageable pageable, Predicate predicate) {
        return this.basePageQuery(countQuery, pageable, predicate, null);

    }

    /**
     * 基础分页查询
     *
     * @param countQuery count查询对象（可能包括完整的sql）
     * @param pageable   分页对象
     * @param predicate  where条件
     * @param orders     排序
     * @return
     */
    protected Page<T> basePageQuery(JPAQuery<T> countQuery, Pageable pageable, Predicate predicate, OrderSpecifier<?>... orders) {
        if (Objects.isNull(countQuery)) {
            throw new NullPointerException("countQuery 不能为空");
        }
        // 加where入条件
        if (Objects.nonNull(predicate)) {
            countQuery.where(predicate);
        }

        // 转成查询对象
        JPQLQuery<T> query = querydsl.applyPagination(pageable, countQuery);
        // 查询对象增加排序
        if (Objects.nonNull(orders)) {
            query.orderBy(orders);
        }
        // 返回分页查询
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);

    }


    /**
     * 获取查询列表数据
     *
     * @param path
     * @return
     */
    protected List<T> baseGetList(EntityPathBase<T> path) {
        return baseGetList(path, null);
    }


    /**
     * 获取查询列表数据
     *
     * @param path
     * @param predicate
     * @return
     */
    protected List<T> baseGetList(EntityPathBase<T> path, Predicate predicate) {
        return baseGetList(path, predicate, null);
    }

    /**
     * 获取查询列表数据
     *
     * @param path
     * @param predicate
     * @param orders
     * @return
     */
    protected List<T> baseGetList(EntityPathBase<T> path, Predicate predicate, OrderSpecifier<?>... orders) {
        JPAQuery<T> jpaQuery = jpaQueryFactory.selectFrom(path);

        // 加where入条件
        if (Objects.nonNull(predicate)) {
            jpaQuery.where(predicate);
        }
        // 排序
        if (Objects.nonNull(orders)) {
            jpaQuery.orderBy(orders);
        }

        return jpaQuery.fetch();
    }


}