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
public class QUserAccountLog extends EntityPathBase<UserAccountLog> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserAccountLog userAccountLog = new QUserAccountLog("userAccountLog");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Integer> source = createNumber("source", Integer.class);

	public final NumberPath<Long> sourceNo = createNumber("sourceNo", Long.class);

	public final NumberPath<Long> amount = createNumber("amount", Long.class);

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
			 case "source":
				return source;
			 case "sourceNo":
				return sourceNo;
			 case "amount":
				return amount;
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

	public QUserAccountLog(String variable) {
		super(UserAccountLog.class, forVariable(variable));
	}

	public QUserAccountLog(Path<? extends UserAccountLog> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserAccountLog(PathMetadata metadata) {
		super(UserAccountLog.class, metadata);
	}
}
