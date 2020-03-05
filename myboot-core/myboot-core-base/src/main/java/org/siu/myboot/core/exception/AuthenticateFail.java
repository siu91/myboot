package org.siu.myboot.core.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证失败
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class AuthenticateFail extends BaseException {

    public AuthenticateFail(String message) {
        super(message, AuthenticateFail.class.getSimpleName().toUpperCase(), "认证失败");
        this.setHttpStatus(HttpStatus.FORBIDDEN);
    }

    public AuthenticateFail() {
        this(null);
    }
}
