package org.siu.myboot.server.entity.dto;

import lombok.Data;

/**
 * 用户的角色、权限信息
 *
 * @Author Siu
 * @Date 2020/3/8 14:08
 * @Version 0.0.1
 */
@Data
public class UserAuthorities {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 权限标识
     */
    private String permit;

}
