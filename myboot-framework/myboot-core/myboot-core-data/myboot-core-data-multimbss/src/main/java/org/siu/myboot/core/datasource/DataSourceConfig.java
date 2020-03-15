package org.siu.myboot.core.datasource;

import org.siu.myboot.core.datasource.dds.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
public class DataSourceConfig {

    @Resource(name = "primaryDataSource")
    DataSource primaryDataSource;

    @Resource(name = "secondaryDataSource")
    DataSource secondaryDataSource;

    @Primary
    @Bean(name = "dataSource")
    @Qualifier("dataSource")
    public DataSource primaryDataSource() {
        return new DynamicDataSource(primaryDataSource, secondaryDataSource);
    }


}
