package org.siu.myboot.componnent.oss.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO自动配置
 *
 * <p>
 * ConditionalOnProperty，这个注解能够控制某个configuration是否生效
 * 具体操作是通过其两个属性name以及havingValue来实现的
 * 中name用来从application.properties中读取某个属性值，如果该值为空，则返回false;如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。如果返回值为false，则该configuration不生效；为true则生效
 *
 * @Author Siu
 * @Date 2020/2/21 9:10
 * @Version 0.0.1
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(name = {"minio.url"})
public class MinioAutoConfig {

    @Autowired
    MinioProperties minioProperties;

    /**
     * MinIo Client
     *
     * @return
     * @throws InvalidPortException
     * @throws InvalidEndpointException
     */
    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(minioProperties.url, minioProperties.accessKey, minioProperties.secretKey);
    }
}
