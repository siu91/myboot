package org.siu.myboot.auth.config;

import org.siu.myboot.core.constant.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * spring security 配置
 *
 * @Author Siu
 * @Date 2020/3/12 22:04
 * @Version 0.0.1
 */
@ConfigurationProperties(prefix = "spring.security")
public class SpringSecurityProperties {

    @PostConstruct
    private void init() {
        if (this.permitAll == null) {
            this.permitAll = new HashSet<>();
        }
        this.permitAll.addAll(Constant.Auth.PERMIT_ALL_API);
    }

    /**
     * 开放无需认证的接口
     */
    private Set<String> permitAll;


    public Set<String> getPermitAll() {
        return permitAll;
    }

    public void setPermitAll(Set<String> permitAll) {
        this.permitAll = permitAll;
    }
}
