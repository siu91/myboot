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
public class QProductPic extends EntityPathBase<ProductPic> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductPic productPic = new QProductPic("productPic");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> productId = createNumber("productId", Long.class);

	public final StringPath picDesc = createString("picDesc");

	public final StringPath picUrl = createString("picUrl");

	public final NumberPath<Integer> mainPic = createNumber("mainPic", Integer.class);

	public final NumberPath<Integer> picOrder = createNumber("picOrder", Integer.class);

	public final NumberPath<Integer> picStatus = createNumber("picStatus", Integer.class);

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
			 case "picDesc":
				return picDesc;
			 case "picUrl":
				return picUrl;
			 case "mainPic":
				return mainPic;
			 case "picOrder":
				return picOrder;
			 case "picStatus":
				return picStatus;
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

	public QProductPic(String variable) {
		super(ProductPic.class, forVariable(variable));
	}

	public QProductPic(Path<? extends ProductPic> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductPic(PathMetadata metadata) {
		super(ProductPic.class, metadata);
	}
}
