package org.siu.myboot.auth;

/**
 * 认证授权常量定义
 *
 * @Author Siu
 * @Date 2020/3/5 10:12
 * @Version 0.0.1
 */
public class Constant {


    /**
     * 请求头token参数
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * token 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * token中权限信息key
     */
    public static final String AUTHORITIES_KEY = "auth";
    /**
     * token中权限信息分割符
     */
    public static final String AUTHORITIES_SPLIT = ",";
}
