package org.siu.myboot.autoconfigure.datasource.peoperties;

/**
 * @Author Siu
 * @Date 2020/2/17 19:16
 * @Version 0.0.1
 */


/**
 * 数据源配置项
 *
 * @Author Siu
 * @Date 2020/2/16 15:31
 * @Version 0.0.1
 */
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
