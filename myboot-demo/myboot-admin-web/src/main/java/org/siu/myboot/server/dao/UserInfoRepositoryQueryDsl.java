package org.siu.myboot.server.dao;

import com.querydsl.core.types.Template;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.server.model.po.QOauths;
import org.siu.myboot.server.model.po.QUserInfo;
import org.siu.myboot.server.model.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

/**
 * QueryDsl 复杂查询
 *
 * @Author Siu
 * @Date 2020/2/22 20:12
 * @Version 0.0.1
 */
@Repository
public class UserInfoRepositoryQueryDsl extends BaseJpaRepository<UserInfo, Integer> {

    public UserInfoRepositoryQueryDsl(EntityManager em) {
        super(UserInfo.class, em);
    }

    /**
     * QUserInfo QOauths 实体是QueryDsl插件自动生成的，插件会自动扫描加了@Entity的实体，生成一个用于查询的EntityPath类
     */

    private static final QUserInfo qUserInfo = QUserInfo.userInfo;
    private static final QOauths qOauths = QOauths.oauths;


    public Page<UserInfo> queryUserOauths(Pageable pageable) {
        JPAQuery countQuery = jpaQueryFactory.
                select(qUserInfo.userId, qUserInfo.avatarUrl)
                .from(qUserInfo)
                .leftJoin(qOauths).on(qUserInfo.userId.eq(qOauths.userId));


        JPQLQuery query = querydsl.applyPagination(pageable, countQuery);
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }
}