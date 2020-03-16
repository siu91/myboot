package org.siu.myboot.core.data.dds.aop;

import org.siu.myboot.core.data.dds.DataSourceId;

import java.lang.annotation.*;

/**
 * 注解切换数据源
 *
 * @Author Siu
 * @Date 2020/3/15 14:51
 * @Version 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataSource {

    /**
     * @return  id of the datasource
     */
    DataSourceId id() default DataSourceId.PRIMARY;
}
