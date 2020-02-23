package org.siu.myboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Data Object 基类
 *
 * @Author Siu
 * @Date 2020/2/20 11:33
 * @Version 0.0.1
 */
@Data
public class BaseEntity {

    @Column(name = "version")
    private Integer version;

    @Column(name = "create_time")
    private Timestamp createTime;
}
