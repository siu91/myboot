package org.siu.myboot.core.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.siu.myboot.core.constant.ResultConstant;

/**
 * @Author Siu
 * @Date 2020/2/23 16:47
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class Result {
    private int code;
    private String message;
    private Object data;
    private Object debug;


    public Result() {
    }

    public Result(Object data) {
        this.code = ResultConstant.SUCCESS;
        this.message = ResultConstant.SUCCESS_MSG;
        this.data = data;
    }
}
