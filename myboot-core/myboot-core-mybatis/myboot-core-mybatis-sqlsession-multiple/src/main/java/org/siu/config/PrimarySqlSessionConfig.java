package org.siu.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis多数据源配置：SqlSession 配置 （primary）
 *
 * @Author Siu
 * @Date 2020/2/18 13:47
 * @Version 0.0.1
 */
@Configuration
@MapperScan(basePackages = "org.siu.**.mapper.primary", sqlSessionTemplateRef  = "primarySqlSessionTemplate")
public class PrimarySqlSessionConfig {

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/primary/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
