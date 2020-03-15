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
public class QRoleAuthority extends EntityPathBase<RoleAuthority> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QRoleAuthority roleAuthority = new QRoleAuthority("roleAuthority");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

	public final StringPath perm = createString("perm");

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
			 case "roleId":
				return roleId;
			 case "perm":
				return perm;
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

	public QRoleAuthority(String variable) {
		super(RoleAuthority.class, forVariable(variable));
	}

	public QRoleAuthority(Path<? extends RoleAuthority> path) {
		super(path.getType(), path.getMetadata());
	}

	public QRoleAuthority(PathMetadata metadata) {
		super(RoleAuthority.class, metadata);
	}
}
