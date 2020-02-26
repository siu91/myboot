package org.siu.myboot.core.dsl;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.SimpleExpression;

/**
 * Query DSL 构建接口
 *
 * @Author Siu
 * @Date 2020/2/26 15:50
 * @Version 0.0.1
 */
public interface QBuiler {

    /**
     * 通过属性名，获取构建排序的 queryDSL 对象
     * @param property
     * @return
     */
    ComparableExpressionBase order(String property);

    /**
     * 通过属性名，获取构建查询条件的 queryDSL 对象
     *
     * @param property
     * @return
     */
    SimpleExpression condition(String property);
}
