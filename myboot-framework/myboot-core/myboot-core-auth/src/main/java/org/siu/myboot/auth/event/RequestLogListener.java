package org.siu.myboot.auth.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听请求日志
 *
 * @Author Siu
 * @Date 2020/3/9 11:14
 * @Version 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class RequestLogListener {

    @Async
    @Order
    @EventListener(RequestEvent.class)
    public void saveSysLog(RequestEvent event) {
        RequestLog requestLog = (RequestLog) event.getSource();
        log.info(requestLog.getUri());
        // TODO 调用service 写入日志
    }
}
