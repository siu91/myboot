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
public class QUserPointLog extends EntityPathBase<UserPointLog> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserPointLog userPointLog = new QUserPointLog("userPointLog");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Integer> pointSource = createNumber("pointSource", Integer.class);

	public final NumberPath<Long> referId = createNumber("referId", Long.class);

	public final NumberPath<Long> changePoint = createNumber("changePoint", Long.class);

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

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
			 case "pointSource":
				return pointSource;
			 case "referId":
				return referId;
			 case "changePoint":
				return changePoint;
			 case "createTime":
				return createTime;
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

	public QUserPointLog(String variable) {
		super(UserPointLog.class, forVariable(variable));
	}

	public QUserPointLog(Path<? extends UserPointLog> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserPointLog(PathMetadata metadata) {
		super(UserPointLog.class, metadata);
	}
}
