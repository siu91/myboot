package org.siu.myboot.auth.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 请求日志
 *
 * @Author Siu
 * @Date 2020/3/6 9:00
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class RequestLog {

    /**
     * 请求的访问者ID（用户名/ID/手机等唯一识别）
     */
    private String uId;

    /**
     * 请求携带的token
     */
    private String jwt;

    /**
     * 父追踪ID
     */
    private String pTraceId;

    /**
     * 父追踪ID
     */
    private String traceId;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 客户端用户代理
     */

    private String userAgent;

    /**
     * 请求的资源
     */
    private String uri;

    /**
     * 资源的请求类型
     */
    private String methodType;

    private Date loginTime;

    private int status;


}
