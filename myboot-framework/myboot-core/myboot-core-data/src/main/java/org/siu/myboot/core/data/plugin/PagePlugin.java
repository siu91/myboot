package org.siu.myboot.core.data.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.siu.myboot.core.data.dds.DataSourceId;
import org.siu.myboot.core.data.dds.DynamicDataSourceHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mybatis分页插件
 *
 * @Author Siu
 * @Date 2020/3/15 14:59
 * @Version 0.0.1
 */
@Slf4j
@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
)
public class PagePlugin implements Interceptor {


    /**
     * 匹配需要处理的方法
     */
    private String methodMatcherRegex = ".*ByPage$";


    /**
     * 执行插件的核心业务(分页)
     * 1、获取原始sql
     * 2、增加 count sql
     * 3、原始sql 加上limit
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        log.info("sql:{}", sql);
        Object paramObj = boundSql.getParameterObject();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // 获取mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取方法名
        String mapperMethodName = mappedStatement.getId();

        if (mapperMethodName.matches(methodMatcherRegex)) {
            // 获取参数
            Map<String, Object> params = (Map<String, Object>) paramObj;
            PageInfo pageInfo = (PageInfo) params.get("page");
            // count sql
            String countSql = "select count(*) from (" + sql + ") a";
            // 执行count TODO 通过connection 判断数据库类型
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countPreparedStatement = connection.prepareStatement(countSql);
            // 获取参数处理器
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            // 处理count sql 中的参数
            parameterHandler.setParameters(countPreparedStatement);
            // 执行count sql
            ResultSet resultSet = countPreparedStatement.executeQuery();
            if (resultSet.next()) {
                pageInfo.setTotal(resultSet.getInt(1));
            }
            resultSet.close();
            countPreparedStatement.close();

            // 原始sql 加上limit
            String limitSql = genLimitSql(sql);
            // 处理过后的sql还给mybatis
            metaObject.setValue("delegate.boundSql.sql", limitSql);
        }


        return invocation.proceed();
    }

    String genLimitSql(String sql) {
        // TODO 处理 limit sql
        return sql;

    }

    /**
     * 把自定义的插件加入插件执行链
     * 见 interceptorChain.pluginAll(executor);
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以从xml中plugin标签中设置properties
        // TODO 传入自定义的匹配方法正则
        this.methodMatcherRegex = "properties.xxxx";
    }
}


