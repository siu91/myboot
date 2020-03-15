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
public class QProduct extends EntityPathBase<Product> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProduct product = new QProduct("product");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> productCode = createNumber("productCode", Long.class);

	public final StringPath productName = createString("productName");

	public final StringPath barCode = createString("barCode");

	public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

	public final NumberPath<Long> category1Id = createNumber("category1Id", Long.class);

	public final NumberPath<Long> category2Id = createNumber("category2Id", Long.class);

	public final NumberPath<Long> category3Id = createNumber("category3Id", Long.class);

	public final NumberPath<Long> supplierId = createNumber("supplierId", Long.class);

	public final NumberPath<Long> price = createNumber("price", Long.class);

	public final NumberPath<Long> originPrice = createNumber("originPrice", Long.class);

	public final NumberPath<Long> averageCost = createNumber("averageCost", Long.class);

	public final NumberPath<Integer> publishStatus = createNumber("publishStatus", Integer.class);

	public final NumberPath<Integer> auditStatus = createNumber("auditStatus", Integer.class);

	public final NumberPath<Double> weight = createNumber("weight", Double.class);

	public final NumberPath<Double> length = createNumber("length", Double.class);

	public final NumberPath<Double> height = createNumber("height", Double.class);

	public final NumberPath<Double> width = createNumber("width", Double.class);

	public final NumberPath<Integer> unit = createNumber("unit", Integer.class);

	public final NumberPath<Integer> color = createNumber("color", Integer.class);

	public final StringPath productionDate = createString("productionDate");

	public final StringPath expirationDate = createString("expirationDate");

	public final StringPath desc = createString("desc");

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
			 case "productCode":
				return productCode;
			 case "productName":
				return productName;
			 case "barCode":
				return barCode;
			 case "brandId":
				return brandId;
			 case "category1Id":
				return category1Id;
			 case "category2Id":
				return category2Id;
			 case "category3Id":
				return category3Id;
			 case "supplierId":
				return supplierId;
			 case "price":
				return price;
			 case "originPrice":
				return originPrice;
			 case "averageCost":
				return averageCost;
			 case "publishStatus":
				return publishStatus;
			 case "auditStatus":
				return auditStatus;
			 case "weight":
				return weight;
			 case "length":
				return length;
			 case "height":
				return height;
			 case "width":
				return width;
			 case "unit":
				return unit;
			 case "color":
				return color;
			 case "productionDate":
				return productionDate;
			 case "expirationDate":
				return expirationDate;
			 case "desc":
				return desc;
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

	public QProduct(String variable) {
		super(Product.class, forVariable(variable));
	}

	public QProduct(Path<? extends Product> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProduct(PathMetadata metadata) {
		super(Product.class, metadata);
	}
}
