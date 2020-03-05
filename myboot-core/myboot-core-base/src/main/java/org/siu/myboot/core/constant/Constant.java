package org.siu.myboot.core.constant;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 常量
 *
 * @Author Siu
 * @Date 2020/2/26 11:02
 * @Version 0.0.1
 */
public class Constant {

    /**
     * 父追踪ID
     */
    public static final String PARENT_TRACE_ID = "p_tid";
    public static final String DEFAULT_PARENT_TRACE_ID = "0";

    /**
     * 追踪ID
     */
    public static final String TRACE_ID = "tid";

    public static final String UNKNOWN = "unknown";


    /**
     * 认证、授权常量定义
     */
    public static class Auth {
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

}
