package org.siu.myboot.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * 请求事件
 *
 * @Author Siu
 * @Date 2020/3/9 10:50
 * @Version 0.0.1
 */
public class RequestEvent extends ApplicationEvent {

    public RequestEvent(RequestLog source) {
        super(source);
    }
}
