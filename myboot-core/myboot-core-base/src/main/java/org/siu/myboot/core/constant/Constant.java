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


    public static final String UNKNOWN = "unknown";


    /**
     * 追踪常量
     */
    public static class Trace {
        /**
         * 父追踪ID
         */
        public static final String PARENT_TRACE_ID = "p_tid";
        public static final String DEFAULT_PARENT_TRACE_ID = "0";

        /**
         * 追踪ID
         */
        public static final String TRACE_ID = "tid";
    }

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
         * token用户的版本信息key
         */
        public static final String VERSION_KEY = "version";
        /**
         * token中权限信息分割符
         */
        public static final String AUTHORITIES_SPLIT = ",";

        /**
         * 刷新token临界值（毫秒）
         */
        public static final int REFRESH_TOKEN_TIME_THRESHOLD_MS = 30 * 60 * 1000;

        /**
         * 每次刷新token最小增加续租时间（毫米）
         */
        public static final int REFRESH_TOKEN_RENEW_TIME_MS = 60 * 60 * 1000;
    }

}
