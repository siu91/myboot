package org.siu.myboot.server.repository.dsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserInfo;
import org.siu.myboot.server.entity.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

/**
 * UserInfo 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-27 20:33:51
 * @Version 0.0.1
 */
@Repository
public class UserInfoRepositoryQueryDsl extends BaseJpaRepository<UserInfo, Long>  {

	public UserInfoRepositoryQueryDsl(EntityManager entityManager) {
		super(UserInfo.class, entityManager);
	}

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
	public Page<UserInfo> queryExample(Pageable pageable) {
		JPAQuery<UserInfo> countQuery = jpaQueryFactory.selectFrom(qUserInfo);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryPage
     *
     * @param pageable
     * @return
     */
    public Page<UserInfo> queryPage(Pageable pageable, UserInfo params) {
        JPAQuery<UserInfo> countQuery = jpaQueryFactory.selectFrom(qUserInfo);
        QueryBuilder.buildCondition(countQuery, qUserInfo, params);
        return basePageQuery(countQuery, pageable);
    }


    /**
     * queryList
     * 
     * @param params
     * @return
     */
    public List<UserInfo> queryList(UserInfo params, List<OrderSpecifier<?>> orderSpecifiers) {
        JPAQuery<UserInfo> query = jpaQueryFactory.selectFrom(qUserInfo);
        QueryBuilder.buildCondition(query, qUserInfo, params);
        if (Objects.nonNull(orderSpecifiers)) {
            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {
                query.orderBy(orderSpecifier);
            }
        }
        return query.fetch();
    }
}
