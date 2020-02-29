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

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath realName = createString("realName");

	public final NumberPath<Integer> idType  = createNumber("idType ", Integer.class);

	public final StringPath idNo  = createString("idNo ");

	public final StringPath email  = createString("email ");

	public final NumberPath<Integer> gender  = createNumber("gender ", Integer.class);

	public final NumberPath<Long> points = createNumber("points", Long.class);

	public final DateTimePath<java.util.Date> registerTime  = createDateTime("registerTime ", java.util.Date.class);

	public final StringPath birthday  = createString("birthday ");

	public final NumberPath<Integer> userLevel  = createNumber("userLevel ", Integer.class);

	public final NumberPath<Integer> userType = createNumber("userType", Integer.class);

	public final NumberPath<Long> accountAmount = createNumber("accountAmount", Long.class);

	public final NumberPath<Long> version = createNumber("version", Long.class);

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
			 case "realName":
				return realName;
			 case "idType ":
				return idType ;
			 case "idNo ":
				return idNo ;
			 case "email ":
				return email ;
			 case "gender ":
				return gender ;
			 case "points":
				return points;
			 case "registerTime ":
				return registerTime ;
			 case "birthday ":
				return birthday ;
			 case "userLevel ":
				return userLevel ;
			 case "userType":
				return userType;
			 case "accountAmount":
				return accountAmount;
			 case "version":
				return version;
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
