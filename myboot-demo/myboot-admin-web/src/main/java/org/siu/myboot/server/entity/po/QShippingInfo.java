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
public class QShippingInfo extends EntityPathBase<ShippingInfo> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QShippingInfo shippingInfo = new QShippingInfo("shippingInfo");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath shippingSn = createString("shippingSn");

	public final StringPath shipName = createString("shipName");

	public final StringPath shipContact = createString("shipContact");

	public final StringPath telephone = createString("telephone");

	public final NumberPath<Long> price = createNumber("price", Long.class);

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
			 case "shippingSn":
				return shippingSn;
			 case "shipName":
				return shipName;
			 case "shipContact":
				return shipContact;
			 case "telephone":
				return telephone;
			 case "price":
				return price;
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

	public QShippingInfo(String variable) {
		super(ShippingInfo.class, forVariable(variable));
	}

	public QShippingInfo(Path<? extends ShippingInfo> path) {
		super(path.getType(), path.getMetadata());
	}

	public QShippingInfo(PathMetadata metadata) {
		super(ShippingInfo.class, metadata);
	}
}
