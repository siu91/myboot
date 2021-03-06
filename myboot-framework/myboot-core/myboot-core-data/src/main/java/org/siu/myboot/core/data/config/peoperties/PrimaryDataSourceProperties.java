package org.siu.myboot.core.data.config.peoperties;

/**
 * @Author Siu
 * @Date 2020/2/17 19:16
 * @Version 0.0.1
 */


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源配置项
 *
 * @Author Siu
 * @Date 2020/2/16 15:31
 * @Version 0.0.1
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.primary")
public class PrimaryDataSourceProperties extends DataSourceProperties {
}
