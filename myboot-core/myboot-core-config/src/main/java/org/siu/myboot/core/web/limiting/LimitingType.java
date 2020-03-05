package org.siu.myboot.core.web.limiting;

/**
 * 限流类型
 *
 * @Author Siu
 * @Date 2020/3/3 11:50
 * @Version 0.0.1
 */
public enum LimitingType {
    /**
     * 自定义key
     */
    CUSTOM,

    /**
     * 根据请求者IP
     */
    IP;

}
