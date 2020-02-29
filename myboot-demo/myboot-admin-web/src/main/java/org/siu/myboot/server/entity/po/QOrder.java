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
public class QOrder extends EntityPathBase<Order> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QOrder order = new QOrder("order");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath orderSn = createString("orderSn");

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath shippingUser = createString("shippingUser");

	public final StringPath province = createString("province");

	public final StringPath city = createString("city");

	public final StringPath district = createString("district");

	public final StringPath address = createString("address");

	public final NumberPath<Integer> paymentMethod = createNumber("paymentMethod", Integer.class);

	public final NumberPath<Long> orderAmount = createNumber("orderAmount", Long.class);

	public final NumberPath<Long> discountAmount = createNumber("discountAmount", Long.class);

	public final NumberPath<Long> shippingAmount = createNumber("shippingAmount", Long.class);

	public final NumberPath<Long> paymentAmount = createNumber("paymentAmount", Long.class);

	public final StringPath shippingCompName = createString("shippingCompName");

	public final StringPath shippingSn = createString("shippingSn");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> shippingTime = createDateTime("shippingTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> payTime = createDateTime("payTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> receiveTime = createDateTime("receiveTime", java.util.Date.class);

	public final NumberPath<Integer> orderStatus = createNumber("orderStatus", Integer.class);

	public final NumberPath<Long> orderPoint = createNumber("orderPoint", Long.class);

	public final NumberPath<Long> invoiceId = createNumber("invoiceId", Long.class);

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
			 case "orderSn":
				return orderSn;
			 case "userId":
				return userId;
			 case "shippingUser":
				return shippingUser;
			 case "province":
				return province;
			 case "city":
				return city;
			 case "district":
				return district;
			 case "address":
				return address;
			 case "paymentMethod":
				return paymentMethod;
			 case "orderAmount":
				return orderAmount;
			 case "discountAmount":
				return discountAmount;
			 case "shippingAmount":
				return shippingAmount;
			 case "paymentAmount":
				return paymentAmount;
			 case "shippingCompName":
				return shippingCompName;
			 case "shippingSn":
				return shippingSn;
			 case "createTime":
				return createTime;
			 case "shippingTime":
				return shippingTime;
			 case "payTime":
				return payTime;
			 case "receiveTime":
				return receiveTime;
			 case "orderStatus":
				return orderStatus;
			 case "orderPoint":
				return orderPoint;
			 case "invoiceId":
				return invoiceId;
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

	public QOrder(String variable) {
		super(Order.class, forVariable(variable));
	}

	public QOrder(Path<? extends Order> path) {
		super(path.getType(), path.getMetadata());
	}

	public QOrder(PathMetadata metadata) {
		super(Order.class, metadata);
	}
}
