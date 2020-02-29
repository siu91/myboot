package org.siu.myboot.core.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 异常基类
 *
 * @Author Siu
 * @Date 2020/2/29 11:53
 * @Version 0.0.1
 */
@Data
public class BaseException extends Exception {
    /**
     * HTTP 状态
     */
    private Integer httpStatus;

    /**
     * 异常信息
     */
    private String errorCode;

    /**
     * debug 信息
     */
    private String devMsg;


    /**
     * 构造函数
     *
     * @param message
     * @param errorCode
     * @param devMsg
     */
    public BaseException(String message, String errorCode, String devMsg) {
        super(message);
        this.errorCode = errorCode;
        this.devMsg = devMsg;
    }


    /**
     * 通常使用内部的定义状态码返回给body
     * <p>
     * 当使用org.springframework.http.HttpStatus ，默认 + 10000
     * 内部异常状态码从 11000 开始
     *
     * @param httpStatus
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus.value() + 10000;
    }

}
