package org.siu.myboot.auth.jwt;

import org.siu.myboot.core.entity.BaseEntity;

/**
 * 请求日志记录
 *
 * @Author Siu
 * @Date 2020/3/6 9:01
 * @Version 0.0.1
 */
public interface RequestLogService {

    /**
     * 记录请求日志
     *
     * @param log
     */
    void log(BaseEntity log);
}
