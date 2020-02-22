package org.siu.myboot.server.model.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author Siu
 * @Date 2020/2/20 11:33
 * @Version 0.0.1
 */
@Entity
public class Oauths {
    private long id;
    private long userId;
    private short oauthType;
    private String oauthId;
    private String unionid;
    private String credential;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "oauth_type")
    public short getOauthType() {
        return oauthType;
    }

    public void setOauthType(short oauthType) {
        this.oauthType = oauthType;
    }

    @Basic
    @Column(name = "oauth_id")
    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    @Basic
    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Basic
    @Column(name = "credential")
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
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
        Oauths oauths = (Oauths) o;
        return id == oauths.id &&
                userId == oauths.userId &&
                oauthType == oauths.oauthType &&
                Objects.equals(oauthId, oauths.oauthId) &&
                Objects.equals(unionid, oauths.unionid) &&
                Objects.equals(credential, oauths.credential) &&
                Objects.equals(createTime, oauths.createTime) &&
                Objects.equals(updateTime, oauths.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, oauthType, oauthId, unionid, credential, createTime, updateTime);
    }
}
