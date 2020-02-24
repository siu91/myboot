package org.siu.myboot.server.entity.po;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

	private static final long serialVersionUID = 1L;

	public static final QUserInfo userInfo = new QUserInfo("userInfo");

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath userName = createString("userName");

	public final StringPath avatarUrl = createString("avatarUrl");

	public final StringPath phone = createString("phone");

	public final StringPath password = createString("password");

	public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

	public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

	public QUserInfo(String variable) {
		super(UserInfo.class, forVariable(variable));
	}

	public QUserInfo(Path<? extends UserInfo> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUserInfo(PathMetadata metadata) {
		super(UserInfo.class, metadata);
	}
}
