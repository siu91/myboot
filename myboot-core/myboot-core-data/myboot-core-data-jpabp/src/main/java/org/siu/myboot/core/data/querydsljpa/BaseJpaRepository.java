package org.siu.myboot.core.data.querydsljpa;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
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
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

/**
 * BaseJpaRepository （JPA + QueryDSL）
 *
 * @Author Siu
 * @Date 2020/2/22 16:48
 * @Version 0.0.1
 */
public class BaseJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

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

    protected Page<T> findAll(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders) {
        final JPAQuery countQuery = jpaQueryFactory.selectFrom(path);
        countQuery.where(predicate);
        JPQLQuery<T> query = querydsl.applyPagination(pageable, countQuery);
        query.orderBy(orders);
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }
}