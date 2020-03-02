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
public class QProductComment extends EntityPathBase<ProductComment> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductComment productComment = new QProductComment("productComment");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> productId = createNumber("productId", Long.class);

	public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

	public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

	public final StringPath title = createString("title");

	public final StringPath content = createString("content");

	public final NumberPath<Integer> auditStatus = createNumber("auditStatus", Integer.class);

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
			 case "orderId":
				return orderId;
			 case "userId":
				return userId;
			 case "title":
				return title;
			 case "content":
				return content;
			 case "auditStatus":
				return auditStatus;
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

	public QProductComment(String variable) {
		super(ProductComment.class, forVariable(variable));
	}

	public QProductComment(Path<? extends ProductComment> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductComment(PathMetadata metadata) {
		super(ProductComment.class, metadata);
	}
}
