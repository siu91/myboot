package org.siu.myboot.core.exception;

import org.springframework.http.HttpStatus;

/**
 * Forbidden 异常
 *
 * @Author Siu
 * @Date 2020/2/29 12:35
 * @Version 0.0.1
 */
public class RequestForbidden extends BaseException {

    public RequestForbidden(String message) {
        super(message, RequestForbidden.class.getSimpleName().toUpperCase(), "请求被拒绝执行");
        this.setHttpStatus(HttpStatus.FORBIDDEN);
    }

    public RequestForbidden() {
        this(null);
    }
}
