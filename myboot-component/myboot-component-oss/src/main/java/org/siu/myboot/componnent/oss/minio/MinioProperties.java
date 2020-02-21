package org.siu.myboot.componnent.oss.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO 配置项
 *
 * @Author Siu
 * @Date 2020/2/21 9:04
 * @Version 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {


    /**
     * minio 服务地址 http://ip:port
     */
    String url = "";

    /**
     * accessKey
     */
    String accessKey = "";

    /**
     * 密码
     */
    String secretKey = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
