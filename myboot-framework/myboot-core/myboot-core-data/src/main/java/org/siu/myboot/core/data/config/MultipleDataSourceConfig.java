package org.siu.myboot.core.data.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.siu.myboot.core.data.config.peoperties.DataSourceProperties;
import org.siu.myboot.core.data.config.peoperties.PrimaryDataSourceProperties;
import org.siu.myboot.core.data.config.peoperties.SecondaryDataSourceProperties;
import org.siu.myboot.core.data.dds.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

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


    // region 配置jpa和mybatis，默认jpa 配置生效

    /**
     * 配置jpa
     *
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactory")
    @ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "jpa", matchIfMissing = true)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .packages("org.siu.myboot.server.entity.po")
                .build();
    }

    /**
     * 配置jpa
     *
     * @param builder
     * @return
     * @throws IOException
     */
    @Bean(name = "transactionManager")
    @ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "jpa", matchIfMissing = true)
    JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws IOException {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
    // endregion

    // region mybatis

    /**
     * 数据源添加到事务(mybatis)
     *
     * @return
     */
    @Bean(name = "transactionManager")
    @ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "mybatis", matchIfMissing = false)
    public DataSourceTransactionManager primaryTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "mybatis", matchIfMissing = false)
    public SqlSessionFactory buildSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "primarySqlSessionTemplate")
    @ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "mybatis", matchIfMissing = false)
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    // endregion


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
