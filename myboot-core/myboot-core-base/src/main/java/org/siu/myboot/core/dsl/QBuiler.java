package org.siu.myboot.core.dsl;

import com.querydsl.core.types.dsl.ComparableExpressionBase;

/**
 * Query DSL 构建接口
 *
 * @Author Siu
 * @Date 2020/2/26 15:50
 * @Version 0.0.1
 */
public interface QBuiler {

    ComparableExpressionBase order(String property);
}
