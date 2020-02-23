package org.siu.myboot.server.repository.dsl;

import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.entity.po.QOauths;
import org.siu.myboot.server.entity.po.QUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * UserInfo 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
@Repository
public class UserInfoRepositoryQueryDsl extends BaseJpaRepository<UserInfo, Long> {

    public UserInfoRepositoryQueryDsl(EntityManager entityManager) {
        super(UserInfo.class, entityManager);
    }

    /**
     * QUserInfo QueryDSL Object: Use jpaQueryFactory build JPAQuery
     */
    private static final QUserInfo qUserInfo = QUserInfo.userInfo;
    private static final QOauths qOauths = QOauths.oauths;

    public Page<UserInfo> queryUserOauths(Pageable pageable) {
        JPAQuery countQuery = jpaQueryFactory
                .select(qUserInfo.avatarUrl).from(qUserInfo)
                .leftJoin(qOauths).on(qUserInfo.userId.eq(qOauths.userId));
        return basePageQuery(countQuery, pageable);

    }
}
