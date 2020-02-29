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
public class QUserOauths extends EntityPathBase<UserOauths> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserOauths userOauths = new QUserOauths("userOauths");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Integer> oauthType = createNumber("oauthType", Integer.class);

	public final StringPath oauthId = createString("oauthId");

	public final StringPath unionid = createString("unionid");

	public final StringPath credential = createString("credential");

	public final NumberPath<Long> version = createNumber("version", Long.class);

	public final NumberPath<Integer> deleteStatus = createNumber("deleteStatus", Integer.class);

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
			 case "oauthType":
				return oauthType;
			 case "oauthId":
				return oauthId;
			 case "unionid":
				return unionid;
			 case "credential":
				return credential;
			 case "version":
				return version;
			 case "deleteStatus":
				return deleteStatus;
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

	public QUserOauths(String variable) {
		super(UserOauths.class, forVariable(variable));
	}

	public QUserOauths(Path<? extends UserOauths> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserOauths(PathMetadata metadata) {
		super(UserOauths.class, metadata);
	}
}
