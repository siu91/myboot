package org.siu.myboot.core.exception;


/**
 * 用户注册异常
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class UserRegisterException extends BaseException {

    public UserRegisterException(String message) {
        super(message, UserRegisterException.class.getSimpleName().toUpperCase(), "注册异常");
        this.setHttpStatus(10603);
    }

    public UserRegisterException() {
        this(null);
    }
}
