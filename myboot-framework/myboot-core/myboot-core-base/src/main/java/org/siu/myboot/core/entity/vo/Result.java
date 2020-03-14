package org.siu.myboot.core.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;
import org.siu.myboot.core.constant.Constant;

import java.io.Serializable;

/**
 * @Author Siu
 * @Date 2020/3/8 21:32
 * @Version 0.0.1
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;


    @Getter
    @Setter
    private T data;

    public static <T> Result<T> ok() {
        return build(null, Constant.Result.SUCCESS, Constant.Result.SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data) {
        return build(data, Constant.Result.SUCCESS, Constant.Result.SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return build(data, Constant.Result.SUCCESS, msg);
    }

    public static <T> Result<T> failed() {
        return build(null, Constant.Result.FAIL, Constant.Result.FAIL_MSG);
    }

    public static <T> Result<T> failed(String msg) {
        return build(null, Constant.Result.FAIL, msg);
    }

    public static <T> Result<T> failed(T data) {
        return build(data, Constant.Result.FAIL, Constant.Result.FAIL_MSG);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return build(data, Constant.Result.FAIL, msg);
    }

    public static <T> Result<T> error(String msg) {
        return build(null, Constant.Result.FAIL, msg);
    }

    public static <T> Result<T> error(T data, String msg) {
        return build(data, Constant.Result.FAIL, msg);
    }

    private static <T> Result<T> build(T data, int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}


