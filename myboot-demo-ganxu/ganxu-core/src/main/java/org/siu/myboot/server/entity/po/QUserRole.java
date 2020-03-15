package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.core.datasource.jpa.querydsljpa.QBuiler;

import java.util.Objects;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRole extends EntityPathBase<UserRole> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserRole userRole = new QUserRole("userRole");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

	public final StringPath role = createString("role");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

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
			 case "userId":
				return userId;
			 case "roleId":
				return roleId;
			 case "role":
				return role;
			 case "createTime":
				return createTime;
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

	public QUserRole(String variable) {
		super(UserRole.class, forVariable(variable));
	}

	public QUserRole(Path<? extends UserRole> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserRole(PathMetadata metadata) {
		super(UserRole.class, metadata);
	}
}
