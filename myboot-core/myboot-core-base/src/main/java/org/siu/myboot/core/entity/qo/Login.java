package org.siu.myboot.core.entity.qo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Siu
 * @Date 2020/3/4 16:26
 * @Version 0.0.1
 */
@Data
public class Login {
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    private Boolean rememberMe;


}
