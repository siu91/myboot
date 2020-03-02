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
public class QProductStock extends EntityPathBase<ProductStock> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductStock productStock = new QProductStock("productStock");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> productId = createNumber("productId", Long.class);

	public final NumberPath<Long> wId = createNumber("wId", Long.class);

	public final NumberPath<Integer> currentCnt = createNumber("currentCnt", Integer.class);

	public final NumberPath<Integer> lockCnt = createNumber("lockCnt", Integer.class);

	public final NumberPath<Integer> inTransitCnt = createNumber("inTransitCnt", Integer.class);

	public final NumberPath<Long> averageCost = createNumber("averageCost", Long.class);

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
			 case "productId":
				return productId;
			 case "wId":
				return wId;
			 case "currentCnt":
				return currentCnt;
			 case "lockCnt":
				return lockCnt;
			 case "inTransitCnt":
				return inTransitCnt;
			 case "averageCost":
				return averageCost;
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

	public QProductStock(String variable) {
		super(ProductStock.class, forVariable(variable));
	}

	public QProductStock(Path<? extends ProductStock> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductStock(PathMetadata metadata) {
		super(ProductStock.class, metadata);
	}
}
