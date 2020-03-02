package org.siu.myboot.server.entity.dto;

import lombok.Data;

/**
 * @Author Siu
 * @Date 2020/3/1 21:10
 * @Version 0.0.1
 */
@Data
public class LoginUserVO {


    /**
     * 用户ID（主键）
     * nullable : false
     * default  : nextval('ganxu_common_seq'::regclass)
     */
    private Long id;

    /**
     * 用户名
     * nullable : false
     * default  : null
     */
    private String userName;


    /**
     * 用户类型
     * nullable : true
     * default  : null
     */
    private Integer userType;


    /**
     * 头像URL
     * nullable : true
     * default  : null
     */
    private String avatarUrl;

    /**
     * 手机
     * nullable : false
     * default  : null
     */
    private String phone;


    /**
     * 删除状态：0-未删除，其它删除
     * nullable : true
     * default  : 0
     */
    private Integer deleteStatus;

    /**
     * 更新时间
     * nullable : true
     * default  : CURRENT_TIMESTAMP
     */
    private java.util.Date updateTime;

}
