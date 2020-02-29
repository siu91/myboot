package org.siu.myboot.core.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.siu.myboot.core.constant.ResultConstant;
import org.siu.myboot.core.exception.BaseException;


/**
 * TODO 1、空值返回格式处理 2、debug 信息处理
 *
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
        this.data = ResultConstant.EMPTY_OBJECT;
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
        this.data = ResultConstant.EMPTY_OBJECT;
        return this;
    }

    /**
     * 未知错误
     *
     * @param e
     * @return
     */
    public Result unknownError(Exception e, boolean debug) {
        this.code = ResultConstant.UNKNOWN_ERROR;
        this.message = "未知错误,请联系管理员.";
        if (debug) {
            this.debug = e.getMessage();
        }
        this.data = ResultConstant.EMPTY_OBJECT;
        return this;
    }

    /**
     * 内部错误
     *
     * @param e
     * @return
     */
    public Result innerError(BaseException e, boolean debug) {
        this.code = e.getHttpStatus();
        this.message = e.getMessage();
        if (debug) {
            this.debug = e.getStackTrace();
        }
        this.data = ResultConstant.EMPTY_OBJECT;
        return this;
    }
}
