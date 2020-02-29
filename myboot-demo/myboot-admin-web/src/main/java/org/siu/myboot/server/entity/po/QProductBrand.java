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
public class QProductBrand extends EntityPathBase<ProductBrand> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductBrand productBrand = new QProductBrand("productBrand");

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final StringPath brandName = createString("brandName");

	public final StringPath telephone = createString("telephone");

	public final StringPath brandWebsite = createString("brandWebsite");

	public final StringPath brandLogo = createString("brandLogo");

	public final StringPath brandDesc = createString("brandDesc");

	public final NumberPath<Integer> brandStatus = createNumber("brandStatus", Integer.class);

	public final NumberPath<Integer> brandOrder = createNumber("brandOrder", Integer.class);

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
			 case "brandName":
				return brandName;
			 case "telephone":
				return telephone;
			 case "brandWebsite":
				return brandWebsite;
			 case "brandLogo":
				return brandLogo;
			 case "brandDesc":
				return brandDesc;
			 case "brandStatus":
				return brandStatus;
			 case "brandOrder":
				return brandOrder;
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

	public QProductBrand(String variable) {
		super(ProductBrand.class, forVariable(variable));
	}

	public QProductBrand(Path<? extends ProductBrand> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductBrand(PathMetadata metadata) {
		super(ProductBrand.class, metadata);
	}
}
