package org.siu.myboot.admin.model.qo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author Siu
 * @Date 2020/2/17 22:13
 * @Version 0.0.1
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserParam extends PageParam {

    private String name;

}
