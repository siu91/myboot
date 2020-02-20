package org.siu.myboot.admin.model.po;

import org.siu.myboot.core.annotation.TableField;
import org.siu.myboot.core.model.BaseDataObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @Author Siu
 * @Date 2020/2/16 13:46
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class User extends BaseDataObject {
    private Long id;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 18, message = "必须年满18岁！")
    private int age;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String pass;

    @TableField(value = "test_1_2")
    private String testCamlStyle;
    @TableField(exist = false)
    private String testCamlStyle2;


    public static void main(String[] args) {
        String test = new User().baseColumnList();
        System.out.println(test);
    }
}
