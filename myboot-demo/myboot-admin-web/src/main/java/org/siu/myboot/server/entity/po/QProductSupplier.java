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
public class QProductSupplier extends EntityPathBase<ProductSupplier> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QProductSupplier productSupplier = new QProductSupplier("productSupplier");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath supplierName = createString("supplierName");

	public final StringPath supplierCode = createString("supplierCode");

	public final NumberPath<Integer> supplierType = createNumber("supplierType", Integer.class);

	public final StringPath contactPerson = createString("contactPerson");

	public final StringPath telephone = createString("telephone");

	public final StringPath bankName = createString("bankName");

	public final StringPath bankAccount = createString("bankAccount");

	public final StringPath address = createString("address");

	public final NumberPath<Integer> supplierStatus = createNumber("supplierStatus", Integer.class);

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
			 case "supplierName":
				return supplierName;
			 case "supplierCode":
				return supplierCode;
			 case "supplierType":
				return supplierType;
			 case "contactPerson":
				return contactPerson;
			 case "telephone":
				return telephone;
			 case "bankName":
				return bankName;
			 case "bankAccount":
				return bankAccount;
			 case "address":
				return address;
			 case "supplierStatus":
				return supplierStatus;
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

	public QProductSupplier(String variable) {
		super(ProductSupplier.class, forVariable(variable));
	}

	public QProductSupplier(Path<? extends ProductSupplier> path) {
		super(path.getType(), path.getMetadata());
	}

	public QProductSupplier(PathMetadata metadata) {
		super(ProductSupplier.class, metadata);
	}
}
