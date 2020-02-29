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
public class QProductCategory extends EntityPathBase<ProductCategory> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductCategory productCategory = new QProductCategory("productCategory");

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final StringPath categoryName = createString("categoryName");

	public final StringPath categoryCode = createString("categoryCode");

	public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

	public final NumberPath<Integer> categoryLevel = createNumber("categoryLevel", Integer.class);

	public final NumberPath<Integer> categoryStatus = createNumber("categoryStatus", Integer.class);

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
			 case "categoryName":
				return categoryName;
			 case "categoryCode":
				return categoryCode;
			 case "parentId":
				return parentId;
			 case "categoryLevel":
				return categoryLevel;
			 case "categoryStatus":
				return categoryStatus;
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

	public QProductCategory(String variable) {
		super(ProductCategory.class, forVariable(variable));
	}

	public QProductCategory(Path<? extends ProductCategory> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductCategory(PathMetadata metadata) {
		super(ProductCategory.class, metadata);
	}
}
