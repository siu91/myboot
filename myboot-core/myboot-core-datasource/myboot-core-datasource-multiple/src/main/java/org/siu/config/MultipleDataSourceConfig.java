package org.siu.config;

import org.siu.datasource.peoperties.DataSourceProperties;
import org.siu.datasource.peoperties.PrimaryDataSourceProperties;
import org.siu.datasource.peoperties.SecondaryDataSourceProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * DataSource 多数据源配置
 *
 * @Author Siu
 * @Date 2020/2/17 12:29
 * @Version 0.0.1
 */
@Configuration
public class MultipleDataSourceConfig {
    @Resource
    PrimaryDataSourceProperties primaryDataSourceProperties;

    @Resource
    SecondaryDataSourceProperties secondaryDataSourceProperties;

    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource primaryDataSource() {
        return createDataSourceBuilder(primaryDataSourceProperties).build();
    }

    /**
     * 使用 ConfigurationProperties 配置出现问题，暂未找到原因（可能时配置前缀时，所有相关的配置只能放一个配置文件？？？）
     *
     * @return
     */
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return createDataSourceBuilder(secondaryDataSourceProperties).build();
    }

    /**
     * 设置 DataSourceBuilder 参数，创建 DataSourceBuilder
     *
     * @param dataSourceProperties 数据源参数
     * @return
     */
    private DataSourceBuilder<?> createDataSourceBuilder(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .driverClassName(dataSourceProperties.getDriverClassName());
    }

}
