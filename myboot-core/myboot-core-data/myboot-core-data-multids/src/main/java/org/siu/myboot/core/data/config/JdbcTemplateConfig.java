package org.siu.myboot.core.data.config;

/**
 * JdbcTemplate 多数据源配置
 *
 * @Author Siu
 * @Date 2020/2/17 12:29
 * @Version 0.0.1
 */
/*@Configuration
@ConditionalOnProperty(name = {"spring.datasource.primary.url","spring.datasource.secondary.url"})
public class JdbcTemplateConfig {


    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}*/
