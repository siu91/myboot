package org.siu.myboot.core.data.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mybatis多数据源配置：DataSourceTransactionManager配置
 *
 * @Author Siu
 * @Date 2020/2/18 13:48
 * @Version 0.0.1
 */
@Configuration
@ConditionalOnProperty(name = {"spring.datasource.primary.url","spring.datasource.secondary.url"})
public class DataSourceTransactionManagerConfig {


    /**
     * 数据源添加到事务（primary）
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 数据源添加到事务（secondary）
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "secondaryTransactionManager")
    @Primary
    public DataSourceTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
