package org.siu.myboot.server.entity.qo;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.server.entity.po.UserInfo;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

	private static final long serialVersionUID = 1L;

	public static final org.siu.myboot.server.entity.qo.QUserInfo userInfo = new org.siu.myboot.server.entity.qo.QUserInfo("userInfo");

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final StringPath userName = createString("userName");

	public final StringPath avatarUrl = createString("avatarUrl");

	public final StringPath phone = createString("phone");

	public final StringPath password = createString("password");

	public final StringPath createTime = createString("createTime");

	public final StringPath updateTime = createString("updateTime");

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
