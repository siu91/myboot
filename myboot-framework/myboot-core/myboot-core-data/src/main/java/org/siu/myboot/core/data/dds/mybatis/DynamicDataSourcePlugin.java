package org.siu.myboot.core.data.dds.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.siu.myboot.core.data.dds.DataSourceId;
import org.siu.myboot.core.data.dds.DynamicDataSourceHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 利用mybatis 拦截器实现数据源切换（读写分离）
 * 这里设置读写分离，主库写，从库读（其它业务需求也可以在此实现，如多个不同数据源操作）
 *
 * 不同数据源（非主从）推荐此实现：org.siu.myboot.core.datasource.dds.aop.ChangeDataSourceAspect
 *
 * <p>
 * Mybatis所有的SQL读写操作，都是通过 org.apache.ibatis.executor.Executor 类来进行操作的。
 * Executor定义了三个方法
 * int update(MappedStatement ms, Object parameter) throws SQLException;
 * <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;
 * <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) thro
 *
 * @Author Siu
 * @Date 2020/3/15 14:59
 * @Version 0.0.1
 */
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class DynamicDataSourcePlugin implements Interceptor {

    /**
     * 缓存 MappedStatement id 和对应的数据源标识
     * 第二次进入拦截的时候就可以从CACHE中直接判断要选择哪个数据源
     */
    private static final Map<String, DataSourceId> CACHE = new ConcurrentHashMap<>();


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        // TODO 判断当前是否有实际事务处于活动状态 true 是，这个逻辑考虑是否要加入判断
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        // ms id
        String msId = ms.getId();
        // 数据源ID
        DataSourceId dsId = CACHE.get(msId);
        // 缓存中未找到
        if (dsId == null) {
            SqlCommandType sqlCommandType = ms.getSqlCommandType();
            if (sqlCommandType.equals(SqlCommandType.SELECT)) {
                // 自增ID查询使用主库
                dsId = msId.contains(SelectKeyGenerator.SELECT_KEY_SUFFIX) ? DataSourceId.PRIMARY : DataSourceId.SECONDARY;
            } else {
                dsId = DataSourceId.PRIMARY;
            }
            // 加入CACHE
            CACHE.put(msId, dsId);
            log.info("方法-[{}]使用数据源-[{}]，SQL类型-[{}]", msId, dsId, sqlCommandType);
        }
        // 选择相应数据源
        DynamicDataSourceHolder.select(dsId);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {
        // do nothing
    }

}


