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
public class QOauths extends EntityPathBase<Oauths> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QOauths oauths = new QOauths("oauths");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Integer> oauthType = createNumber("oauthType", Integer.class);

	public final StringPath oauthId = createString("oauthId");

	public final StringPath unionid = createString("unionid");

	public final StringPath credential = createString("credential");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

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
	public SimpleExpression condition(String property) {
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
			 case "createTime":
				return createTime;
			 case "updateTime":
				return updateTime;
			default:
				return null;
		}
	}

	public QOauths(String variable) {
		super(Oauths.class, forVariable(variable));
	}

	public QOauths(Path<? extends Oauths> path) {
		super(path.getType(), path.getMetadata());
	}

	public QOauths(PathMetadata metadata) {
		super(Oauths.class, metadata);
	}
}
