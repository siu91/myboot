package org.siu.myboot.server.repository.dsl;

import com.querydsl.jpa.impl.JPAQuery;
import org.siu.myboot.core.data.querydsljpa.BaseJpaRepository;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.server.entity.po.QUserInfo;
import org.siu.myboot.server.entity.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * UserInfo 自定义Repository QueryDSL层
 *
 * @author @Author Siu
 * @Date 2020-02-26 22:12:56
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
		JPAQuery countQuery = jpaQueryFactory.selectFrom(qUserInfo);
		return basePageQuery(countQuery, pageable);
	}

    /**
     * queryExample
     *
     * @param pageable
     * @return
     */
    public Page<UserInfo> query(Pageable pageable, UserInfo params) {
        JPAQuery countQuery = jpaQueryFactory.selectFrom(qUserInfo);
        QueryBuilder.buildCondition(countQuery, qUserInfo, params);
        return basePageQuery(countQuery, pageable);
    }
}
