package org.siu.myboot.autoconfigure.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis多数据源配置：SqlSession 配置 （secondary）
 *
 * @Author Siu
 * @Date 2020/2/18 13:47
 * @Version 0.0.1
 */
@Configuration
@ConditionalOnProperty(name = {"spring.datasource.primary.url", "spring.datasource.secondary.url"})
@MapperScan(basePackages = "org.siu.myboot.**.daosecondary", sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondarySqlSessionConfig {

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/secondary/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate buildSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
