package org.siu.myboot.auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @Author Siu
 * @Date 2020/3/7 14:18
 * @Version 0.0.1
 */
public class AuthUser extends User {

    /**
     * 用户名
     */
    //  private String username;

    /**
     * 更新版本
     */
    private long version = -1;

    /**
     * 认证&授权的时间
     */
    private Date authTime;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long version) {
        super(username, password, authorities);
        this.version = version;
        //  this.username = username;
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, long version) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.version = version;
        //this.username = username;
    }

    public long getVersion() {
        return version;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public AuthUser setAuthTime(Date authTime) {
        this.authTime = authTime;
        return this;
    }
}
