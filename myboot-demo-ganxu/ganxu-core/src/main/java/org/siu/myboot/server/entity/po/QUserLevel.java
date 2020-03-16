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
public class QUserLevel extends EntityPathBase<UserLevel> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserLevel userLevel = new QUserLevel("userLevel");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath levelName  = createString("levelName ");

	public final NumberPath<Long> minPoint = createNumber("minPoint", Long.class);

	public final NumberPath<Long> maxPoint = createNumber("maxPoint", Long.class);

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

	public final NumberPath<Integer> levelCode = createNumber("levelCode", Integer.class);

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
			 case "levelName ":
				return levelName ;
			 case "minPoint":
				return minPoint;
			 case "maxPoint":
				return maxPoint;
			 case "createTime":
				return createTime;
			 case "updateTime":
				return updateTime;
			 case "levelCode":
				return levelCode;
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

	public QUserLevel(String variable) {
		super(UserLevel.class, forVariable(variable));
	}

	public QUserLevel(Path<? extends UserLevel> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserLevel(PathMetadata metadata) {
		super(UserLevel.class, metadata);
	}
}
