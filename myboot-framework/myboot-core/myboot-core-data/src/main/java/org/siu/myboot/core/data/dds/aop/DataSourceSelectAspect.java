package org.siu.myboot.core.data.dds.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.siu.myboot.core.data.dds.DynamicDataSourceHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 数据切换切面
 * <p>
 * 该切面应当先于 @Transactional 执行
 * <p>
 *
 * @Author Siu
 * @Date 2020/3/3 12:10
 * @Version 0.0.1
 */
@Slf4j
@Component
@Order(-1)
@Aspect
public class DataSourceSelectAspect {

    /**
     * 设置数据源
     *
     * @param joinPoint
     * @return
     */
    @Before("@annotation(dataSource))")
    public void changeDataSource(JoinPoint joinPoint, DataSource dataSource) {
        DynamicDataSourceHolder.select(dataSource.id());

    }


    /**
     * 清除数据源
     *
     * @param joinPoint
     * @return
     */
    @After("@annotation(org.siu.myboot.core.data.dds.aop.DataSource))")
    public void resetDataSource(JoinPoint joinPoint) {
        DynamicDataSourceHolder.clear();
    }


}
