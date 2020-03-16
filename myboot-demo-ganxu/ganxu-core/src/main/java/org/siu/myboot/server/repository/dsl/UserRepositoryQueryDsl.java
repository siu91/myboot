package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.siu.myboot.core.data.jpa.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.data.jpa.utils.QueryBuilder;
import org.siu.myboot.server.entity.dto.UserAuthorities;
import org.siu.myboot.server.entity.po.*;
import org.siu.myboot.server.entity.dto.LoginUserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
     * QUserRole QueryDSL Object: Use jpaQueryFactory build JPAQuery
     */
    private static final QUserRole qUserRole = QUserRole.userRole;

    /**
     * QRoleAuthority QueryDSL Object: Use jpaQueryFactory build JPAQuery
     */
    private static final QRoleAuthority qRoleAuthority = QRoleAuthority.roleAuthority;


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


    /**
     * 通过登录ID（用户名、手机）、密码查找用户信息
     *
     * @param ids  用户名/手机
     * @param pass 密码
     * @return
     */
    public LoginUserVO findUserInfoByIDsAndPass(String ids, String pass) {

        JPAQuery<LoginUserVO> query = jpaQueryFactory.
                select(Projections.bean(
                        // 映射类型
                        LoginUserVO.class,
                        // 查询字段
                        qUser.id, qUser.userName, qUserInfo.userType, qUser.updateTime, qUser.avatarUrl, qUser.phone))
                .from(qUser, qUserInfo)
                .where(qUser.userName.eq(ids).or(qUser.phone.eq(ids))).where(qUser.password.eq(pass)).where(qUser.id.eq(qUserInfo.userId));

        return query.fetchOne();
    }

    /**
     * 修改密码
     *
     * @param username
     * @param newPassword
     * @param version
     * @return 更新后的版本号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    public long changePassword(String username, String newPassword, long version) {
        JPAUpdateClause update;

        if (version >= 0) {
            update = jpaQueryFactory.update(qUser).set(qUser.password, newPassword)
                    .set(qUser.version, version + 1)
                    .where(qUser.userName.eq(username).and(qUser.version.eq(version)));
        } else {
            update = jpaQueryFactory.update(qUser).set(qUser.password, newPassword)
                    .where(qUser.userName.eq(username));
        }

        long result = update.execute();
        if (result > 0 && version >= 0) {
            return version + 1;
        } else {
            return -1;
        }

    }


    /**
     * 获取用户的权限
     *
     * @return
     */
    public List<UserAuthorities> findUserAuthorities(String username) {
        JPAQuery<UserAuthorities> query = jpaQueryFactory.
                select(Projections.bean(
                        // 映射类型
                        UserAuthorities.class,
                        // 查询字段
                        qUserRole.id.as("roleId"), qUserRole.role, qRoleAuthority.perm.as("permit")))
                .from(qUser, qUserInfo)
                .innerJoin(qUserRole).on(qUser.id.eq(qUserRole.userId).and(qUser.userName.eq(username).or(qUser.phone.eq(username))))
                .leftJoin(qRoleAuthority).on(qUserRole.id.eq(qRoleAuthority.roleId));

        return query.fetch();
    }
}
