package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.core.data.querydsljpa.QBuiler;

import java.util.Objects;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLoginLog extends EntityPathBase<UserLoginLog> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserLoginLog userLoginLog = new QUserLoginLog("userLoginLog");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final DateTimePath<java.util.Date> loginTime = createDateTime("loginTime", java.util.Date.class);

	public final StringPath clientIp = createString("clientIp");

	public final StringPath userAgent = createString("userAgent");

	public final NumberPath<Integer> loginStatus = createNumber("loginStatus", Integer.class);

	/**
	 * get path by property
	 *
	 * @param property
	 * @return
	 */
	private Path path(String property) {
		if (Objects.isNull(property)) {
			return null;
		}
		switch (property) {
			 case "id":
				return id;
			 case "userId":
				return userId;
			 case "loginTime":
				return loginTime;
			 case "clientIp":
				return clientIp;
			 case "userAgent":
				return userAgent;
			 case "loginStatus":
				return loginStatus;
			default:
				return null;
		}
	}

	/**
	 * get property
	 *
	 * @param property
	 * @return
	 */
	@Override
	public ComparableExpressionBase order(String property) {
		 return (ComparableExpressionBase) this.path(property);
	}

	/**
	 * get property
	 *
	 * @param property
	 * @return
	 */
	@Override
	public SimpleExpression condition(String property) {
		 return (SimpleExpression) this.path(property);
	}

	public QUserLoginLog(String variable) {
		super(UserLoginLog.class, forVariable(variable));
	}

	public QUserLoginLog(Path<? extends UserLoginLog> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserLoginLog(PathMetadata metadata) {
		super(UserLoginLog.class, metadata);
	}
}
