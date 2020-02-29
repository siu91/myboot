package org.siu.myboot.core.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.siu.myboot.core.constant.ResultConstant;
import org.siu.myboot.core.exception.BaseException;

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

    public Result success() {
        this.code = ResultConstant.SUCCESS;
        this.message = ResultConstant.SUCCESS_MSG;
        return this;
    }

    /**
     * 参数校验错误返回
     *
     * @param msg
     * @return
     */
    public Result paramsError(String msg) {
        this.code = ResultConstant.SUCCESS;
        this.message = msg;
        return this;
    }

    /**
     * 未知错误
     *
     * @param e
     * @return
     */
    public Result unknownError(Exception e) {
        this.code = ResultConstant.UNKNOWN_ERROR;
        this.message = "未知错误,请联系管理员.";
        this.debug = e.getMessage();
        return this;
    }

    /**
     * 内部错误
     *
     * @param e
     * @return
     */
    public Result innerError(BaseException e) {
        this.code = ResultConstant.INNER_ERROR;
        this.message = "内部错误";
        this.debug = e.getMessage();
        return this;
    }
}
