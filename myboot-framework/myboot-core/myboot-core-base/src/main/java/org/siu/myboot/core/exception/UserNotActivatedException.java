package org.siu.myboot.core.exception;


/**
 * 用户未激活异常
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class UserNotActivatedException extends BaseException {

    public UserNotActivatedException(String message) {
        super(message, UserNotActivatedException.class.getSimpleName().toUpperCase(), "用户未被激活");
        this.setHttpStatus(10602);
    }

    public UserNotActivatedException() {
        this(null);
    }
}
