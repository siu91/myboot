package org.siu.myboot.server.entity.qo;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import org.siu.myboot.server.entity.po.Oauths;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOauths extends EntityPathBase<Oauths> {

	private static final long serialVersionUID = 1L;

	public static final org.siu.myboot.server.entity.qo.QOauths oauths = new org.siu.myboot.server.entity.qo.QOauths("oauths");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> userId = createNumber("userId", Long.class);

	public final NumberPath<Integer> oauthType = createNumber("oauthType", Integer.class);

	public final StringPath oauthId = createString("oauthId");

	public final StringPath unionid = createString("unionid");

	public final StringPath credential = createString("credential");

	public final StringPath createTime = createString("createTime");

	public final StringPath updateTime = createString("updateTime");

	public QOauths(String variable) {
		super(Oauths.class, forVariable(variable));
	}

	public QOauths(Path<? extends Oauths> path) {
		super(path.getType(), path.getMetadata());
	}

	public QOauths(PathMetadata metadata) {
		super(Oauths.class, metadata);
	}
}
