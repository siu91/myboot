package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.core.data.querydsljpa.QBuiler;

import java.util.Objects;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderCart extends EntityPathBase<OrderCart> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QOrderCart orderCart = new QOrderCart("orderCart");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Long> productId = createNumber("productId", Long.class);

	public final NumberPath<Integer> productAmount = createNumber("productAmount", Integer.class);

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
			 case "userId":
				return userId;
			 case "productId":
				return productId;
			 case "productAmount":
				return productAmount;
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

	public QOrderCart(String variable) {
		super(OrderCart.class, forVariable(variable));
	}

	public QOrderCart(Path<? extends OrderCart> path) {
		super(path.getType(), path.getMetadata());
	}

	public QOrderCart(PathMetadata metadata) {
		super(OrderCart.class, metadata);
	}
}
