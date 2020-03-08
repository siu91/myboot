package org.siu.myboot.core.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;

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
        return build(null, 0, null);
    }

    public static <T> Result<T> ok(T data) {
        return build(data, 0, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return build(data, 0, msg);
    }

    public static <T> Result<T> failed() {
        return build(null, -1, null);
    }

    public static <T> Result<T> failed(String msg) {
        return build(null, -1, msg);
    }

    public static <T> Result<T> failed(T data) {
        return build(data, -1, null);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return build(data, -1, msg);
    }

    public static <T> Result<T> error(String msg) {
        return build(null, -1, msg);
    }

    private static <T> Result<T> build(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}


