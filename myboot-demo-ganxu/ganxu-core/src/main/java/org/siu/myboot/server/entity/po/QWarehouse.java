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
public class QWarehouse extends EntityPathBase<Warehouse> implements QBuiler {

	private static final long serialVersionUID = 1L;

	public static final QWarehouse warehouse = new QWarehouse("warehouse");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath warehouseSn = createString("warehouseSn");

	public final StringPath warehoustName = createString("warehoustName");

	public final StringPath warehousePhone = createString("warehousePhone");

	public final StringPath contact = createString("contact");

	public final StringPath province = createString("province");

	public final StringPath city = createString("city");

	public final StringPath district = createString("district");

	public final StringPath address = createString("address");

	public final NumberPath<Integer> warehouseStatus = createNumber("warehouseStatus", Integer.class);

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
			 case "warehouseSn":
				return warehouseSn;
			 case "warehoustName":
				return warehoustName;
			 case "warehousePhone":
				return warehousePhone;
			 case "contact":
				return contact;
			 case "province":
				return province;
			 case "city":
				return city;
			 case "district":
				return district;
			 case "address":
				return address;
			 case "warehouseStatus":
				return warehouseStatus;
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

	public QWarehouse(String variable) {
		super(Warehouse.class, forVariable(variable));
	}

	public QWarehouse(Path<? extends Warehouse> path) {
		super(path.getType(), path.getMetadata());
	}

	public QWarehouse(PathMetadata metadata) {
		super(Warehouse.class, metadata);
	}
}
