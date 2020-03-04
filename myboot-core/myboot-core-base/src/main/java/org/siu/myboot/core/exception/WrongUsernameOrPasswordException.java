package org.siu.myboot.core.exception;


/**
 * 用户名或密码错误
 *
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class WrongUsernameOrPasswordException extends BaseException {

    public WrongUsernameOrPasswordException(String message) {
        super(message, WrongUsernameOrPasswordException.class.getSimpleName().toUpperCase(), "用户名或密码错误");
        this.setHttpStatus(10603);
    }

    public WrongUsernameOrPasswordException() {
        this(null);
    }
}
