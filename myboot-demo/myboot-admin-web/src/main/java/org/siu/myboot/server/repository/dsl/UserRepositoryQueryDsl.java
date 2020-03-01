package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUser;
import org.siu.myboot.server.entity.po.QUserInfo;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * User 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Repository
public class UserRepositoryQueryDsl extends BaseJpaRepository<User, Long> {

    public UserRepositoryQueryDsl(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    /**
     * QUser QueryDSL Object: Use jpaQueryFactory build JPAQuery
     */
    private static final QUser qUser = QUser.user;

    /**
     * QUserInfo QueryDSL Object: Use jpaQueryFactory build JPAQuery
     */
    private static final QUserInfo qUserInfo = QUserInfo.userInfo;

    /**
     * queryExample
     *
     * @param pageable
     * @return
     */
    public Page<User> queryExample(Pageable pageable) {
        JPAQuery<User> countQuery = jpaQueryFactory.selectFrom(qUser);
        return basePageQuery(countQuery, pageable);
    }

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<User> queryPage(Pageable pageable, User params) {
        JPAQuery<User> countQuery = jpaQueryFactory.selectFrom(qUser);
        QueryBuilder.buildCondition(countQuery, qUser, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     *
     * @param params
     * @return
     */
    public List<User> queryList(User params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<User> query = jpaQueryFactory.selectFrom(qUser);
        QueryBuilder.buildCondition(query, qUser, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }


    public UserVO loginWithUsernameOrPhone(String id, String pass) {

        JPAQuery<UserVO> query = jpaQueryFactory.
                select(Projections.bean(
                        // 映射类型
                        UserVO.class,
                        // 查询字段
                        qUser.id, qUser.userName, qUserInfo.userType,qUser.updateTime, qUser.avatarUrl, qUser.phone))
                .from(qUser, qUserInfo)
                .where(qUser.userName.eq(id).or(qUser.phone.eq(id))).where(qUser.password.eq(pass)).where(qUser.id.eq(qUserInfo.userId));

        return query.fetchOne();
    }
}
