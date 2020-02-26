package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.core.dsl.QBuiler;

import java.util.Objects;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserInfo userInfo = new QUserInfo("userInfo");

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath userName = createString("userName");

	public final StringPath avatarUrl = createString("avatarUrl");

	public final StringPath phone = createString("phone");

	public final StringPath password = createString("password");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

	public final NumberPath<Long> softDelete = createNumber("softDelete", Long.class);

	/**
	 * get property
	 *
	 * @param property
	 * @return
	 */
	@Override
	public ComparableExpressionBase order(String property) {
		if (Objects.isNull(property)) {
			return null;
		}
		switch (property) {
			 case "userId":
				return userId;
			 case "userName":
				return userName;
			 case "avatarUrl":
				return avatarUrl;
			 case "phone":
				return phone;
			 case "password":
				return password;
			 case "createTime":
				return createTime;
			 case "updateTime":
				return updateTime;
			 case "softDelete":
				return softDelete;
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
	public SimpleExpression condition(String property) {
		if (Objects.isNull(property)) {
			return null;
		}
		switch (property) {
			 case "userId":
				return userId;
			 case "userName":
				return userName;
			 case "avatarUrl":
				return avatarUrl;
			 case "phone":
				return phone;
			 case "password":
				return password;
			 case "createTime":
				return createTime;
			 case "updateTime":
				return updateTime;
			 case "softDelete":
				return softDelete;
			default:
				return null;
		}
	}

	public QUserInfo(String variable) {
		super(UserInfo.class, forVariable(variable));
	}

	public QUserInfo(Path<? extends UserInfo> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserInfo(PathMetadata metadata) {
		super(UserInfo.class, metadata);
	}
}
