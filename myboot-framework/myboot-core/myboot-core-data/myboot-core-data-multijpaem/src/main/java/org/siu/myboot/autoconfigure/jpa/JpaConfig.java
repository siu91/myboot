package org.siu.myboot.autoconfigure.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 创建数据库 1 和 2
 * 配置多数据源
 * 不同源的 repository 放入不同包路径
 * 声明不同的包路径下使用不同的数据源、事务支持
 * 不同的包路径下创建对应的 repository
 *
 * @Author Siu
 * @Date 2020/2/20 21:13
 * @Version 0.0.1
 */
@Configuration
@ConditionalOnProperty(name = {"spring.datasource.primary.url", "spring.datasource.secondary.url"})
public class JpaConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;


    @Bean(name = "vendorProperties")
    public Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }
}
