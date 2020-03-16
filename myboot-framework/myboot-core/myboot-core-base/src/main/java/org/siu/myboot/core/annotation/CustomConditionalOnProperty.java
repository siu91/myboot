package org.siu.myboot.core.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 自定义 ConditionalOnProperty
 * 支持多个havingValue
 *
 * @Author Siu
 * @Date 2020/3/16 13:58
 * @Version 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(CustomOnPropertyCondition.class)
public @interface CustomConditionalOnProperty {
    /**
     * 条件变量的name
     */
    String name() default "";

    /**
     * havingValue数组，支持or匹配
     */
    String[] havingValue() default {};
}
