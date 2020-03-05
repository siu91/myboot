package org.siu.myboot.server.entity.qo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.siu.myboot.core.valid.Valid;

import javax.validation.constraints.NotNull;

/**
 * 注册用户
 *
 * @Author Siu
 * @Date 2020/3/5 14:41
 * @Version 0.0.1
 */
@Data
public class RegisteredUser {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {Valid.CREATE.class})
    private String userName;

    /**
     * 手机
     */
    @NotNull(message = "手机不能为空", groups = {Valid.CREATE.class})
    @Length(min = 11, max = 11, message = "手机号长度不对", groups = {Valid.CREATE.class})
    private String phone;

    /**
     * 密码
     * nullable : true
     * default  : null
     */
    @NotNull(message = "用户名不能为空", groups = {Valid.CREATE.class})
    private String password;

    /**
     * 用户类型
     * 0-普通用户（默认），10-管理员用户
     */
    private int userType;

}
