package org.siu.myboot.core.datasource.config;

import org.siu.myboot.core.datasource.config.peoperties.DataSourceProperties;
import org.siu.myboot.core.datasource.config.peoperties.PrimaryDataSourceProperties;
import org.siu.myboot.core.datasource.config.peoperties.SecondaryDataSourceProperties;
import org.siu.myboot.core.datasource.dds.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * DataSource 多数据源配置
 * <p>
 * 不存在以下两个配置项时，配置不生效
 * "spring.datasource.primary.url","spring.datasource.secondary.url"
 *
 * @Author Siu
 * @Date 2020/2/17 12:29
 * @Version 0.0.1
 */
@Configuration
@ConditionalOnProperty(name = {"spring.datasource.primary.url", "spring.datasource.secondary.url"})
public class MultipleDataSourceConfig {

    @Resource
    PrimaryDataSourceProperties primaryDataSourceProperties;

    @Resource
    SecondaryDataSourceProperties secondaryDataSourceProperties;

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return createDataSourceBuilder(primaryDataSourceProperties).build();
    }

    /**
     * 使用 ConfigurationProperties 配置出现问题，暂未找到原因（可能时配置前缀时，所有相关的配置只能放一个配置文件？？？）
     * //@ConfigurationProperties(prefix = "spring.datasource.secondary")
     *
     * @return
     */
    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        return createDataSourceBuilder(secondaryDataSourceProperties).build();
    }


    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        return new DynamicDataSource(primaryDataSource(), secondaryDataSource());
    }


    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(dynamicDataSource());
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
