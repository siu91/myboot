package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.core.data.jpa.querydsljpa.QBuiler;

import java.util.Objects;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUser user = new QUser("user");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath userName = createString("userName");

	public final StringPath avatarUrl = createString("avatarUrl");

	public final StringPath phone = createString("phone");

	public final StringPath password = createString("password");

	public final NumberPath<Long> version = createNumber("version", Long.class);

	public final NumberPath<Integer> deleteStatus = createNumber("deleteStatus", Integer.class);

	public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

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
			 case "userName":
				return userName;
			 case "avatarUrl":
				return avatarUrl;
			 case "phone":
				return phone;
			 case "password":
				return password;
			 case "version":
				return version;
			 case "deleteStatus":
				return deleteStatus;
			 case "updateTime":
				return updateTime;
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

	public QUser(String variable) {
		super(User.class, forVariable(variable));
	}

	public QUser(Path<? extends User> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUser(PathMetadata metadata) {
		super(User.class, metadata);
	}
}
