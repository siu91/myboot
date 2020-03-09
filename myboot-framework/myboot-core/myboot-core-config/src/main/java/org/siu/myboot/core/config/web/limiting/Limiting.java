package org.siu.myboot.core.config.web.limiting;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 * <p>
 * 默认每秒10次请求
 *
 * @Author Siu
 * @Date 2020/3/3 11:48
 * @Version 0.0.1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limiting {
    /**
     * 资源的名字
     *
     * @return String
     */
    String name() default "";

    /**
     * 资源的key
     *
     * @return String
     */
    String key() default "";

    /**
     * Key的prefix
     *
     * @return String
     */
    String prefix() default "";

    /**
     * 类型
     * <p>
     * 默认是自定义
     *
     * @return LimitType
     */
    LimitingType type() default LimitingType.CUSTOM;

    /**
     * 给定的时间段
     * 单位秒
     *
     * @return int
     */
    int period() default 1;

    /**
     * 最多的访问限制次数
     *
     * @return int
     */
    int limit() default 10;

}
