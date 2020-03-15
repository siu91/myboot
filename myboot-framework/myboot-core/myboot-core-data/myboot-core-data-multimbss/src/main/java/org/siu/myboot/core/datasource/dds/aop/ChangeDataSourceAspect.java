package org.siu.myboot.core.datasource.dds.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.siu.myboot.core.datasource.dds.DynamicDataSourceHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 数据切换切面
 * <p>
 * 该切面应当先于 @Transactional 执行
 *
 * @Author Siu
 * @Date 2020/3/3 12:10
 * @Version 0.0.1
 */
@Slf4j
@Component
@Order(-1)
@Aspect
public class ChangeDataSourceAspect {

    /**
     * 设置数据源
     *
     * @param pjp
     * @return
     */
    @Before("@annotation(dataSource))")
    public Object changeDataSource(ProceedingJoinPoint pjp, DataSource dataSource) throws Throwable {
        DynamicDataSourceHolder.select(dataSource.id());
        return pjp.proceed();

    }


    /**
     * 清除数据源
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @After("@annotation(org.siu.myboot.core.datasource.dds.aop.DataSource))")
    public Object resetDataSource(ProceedingJoinPoint pjp) throws Throwable {
        DynamicDataSourceHolder.clear();
        return pjp.proceed();

    }


}
