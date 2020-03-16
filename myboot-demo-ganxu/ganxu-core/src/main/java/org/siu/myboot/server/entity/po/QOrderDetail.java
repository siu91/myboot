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
public class QOrderDetail extends EntityPathBase<OrderDetail> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QOrderDetail orderDetail = new QOrderDetail("orderDetail");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

	public final NumberPath<Long> productId = createNumber("productId", Long.class);

	public final StringPath productName = createString("productName");

	public final NumberPath<Integer> productCnt = createNumber("productCnt", Integer.class);

	public final NumberPath<Long> productPrice = createNumber("productPrice", Long.class);

	public final NumberPath<Long> averageCost = createNumber("averageCost", Long.class);

	public final NumberPath<Double> weight = createNumber("weight", Double.class);

	public final NumberPath<Long> discountAmount = createNumber("discountAmount", Long.class);

	public final NumberPath<Long> wId = createNumber("wId", Long.class);

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
			 case "orderId":
				return orderId;
			 case "productId":
				return productId;
			 case "productName":
				return productName;
			 case "productCnt":
				return productCnt;
			 case "productPrice":
				return productPrice;
			 case "averageCost":
				return averageCost;
			 case "weight":
				return weight;
			 case "discountAmount":
				return discountAmount;
			 case "wId":
				return wId;
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

	public QOrderDetail(String variable) {
		super(OrderDetail.class, forVariable(variable));
	}

	public QOrderDetail(Path<? extends OrderDetail> path) {
		super(path.getType(), path.getMetadata());
	}

	public QOrderDetail(PathMetadata metadata) {
		super(OrderDetail.class, metadata);
	}
}
