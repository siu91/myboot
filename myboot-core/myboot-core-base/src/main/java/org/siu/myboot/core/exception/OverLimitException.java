package org.siu.myboot.core.exception;


/**
 * Forbidden 异常
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class OverLimitException extends BaseException {

    public OverLimitException(String message) {
        super(message, OverLimitException.class.getSimpleName().toUpperCase(), "请求被限流");
        this.setHttpStatus(10601);
    }

    public OverLimitException() {
        this(null);
    }
}
