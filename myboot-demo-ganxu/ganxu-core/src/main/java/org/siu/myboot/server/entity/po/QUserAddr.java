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
public class QUserAddr extends EntityPathBase<UserAddr> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QUserAddr userAddr = new QUserAddr("userAddr");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath zip = createString("zip");

	public final StringPath province = createString("province");

	public final StringPath city = createString("city");

	public final StringPath district = createString("district");

	public final StringPath address = createString("address");

	public final StringPath defaultAddr  = createString("defaultAddr ");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> upateTime = createDateTime("upateTime", java.util.Date.class);

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
			 case "zip":
				return zip;
			 case "province":
				return province;
			 case "city":
				return city;
			 case "district":
				return district;
			 case "address":
				return address;
			 case "defaultAddr ":
				return defaultAddr ;
			 case "createTime":
				return createTime;
			 case "upateTime":
				return upateTime;
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

	public QUserAddr(String variable) {
		super(UserAddr.class, forVariable(variable));
	}

	public QUserAddr(Path<? extends UserAddr> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserAddr(PathMetadata metadata) {
		super(UserAddr.class, metadata);
	}
}
