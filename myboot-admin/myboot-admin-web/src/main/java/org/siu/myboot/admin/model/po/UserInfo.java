package org.siu.myboot.admin.model.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author Siu
 * @Date 2020/2/20 11:30
 * @Version 0.0.1
 */
@Entity
@Table(name = "user_info", schema = "public", catalog = "primary")
public class UserInfo {


    private long userId;
    private String userName;
    private String avatarUrl;
    private String phone;
    private String password;
    private Timestamp createTime;
    private Timestamp updateTime;



    @Id
    @SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return userId == userInfo.userId &&
                Objects.equals(userName, userInfo.userName) &&
                Objects.equals(avatarUrl, userInfo.avatarUrl) &&
                Objects.equals(phone, userInfo.phone) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(createTime, userInfo.createTime) &&
                Objects.equals(updateTime, userInfo.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, avatarUrl, phone, password, createTime, updateTime);
    }
}
