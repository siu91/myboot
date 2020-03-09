package org.siu.myboot.core.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 常量
 *
 * @Author Siu
 * @Date 2020/2/26 11:02
 * @Version 0.0.1
 */
public class Constant {


    public static final String UNKNOWN = "unknown";



    public static class Result{
        /**
         * 成功返回码
         */
        public static final int SUCCESS = 0;
        public static final int UNKNOWN_ERROR = 11500;
        public static final int INNER_ERROR = 11501;
        /**
         * 默认失败返回码 未知错误
         */
        public static final int FAIL = -1;

        public static final String SUCCESS_MSG = "SUCCESS";
        public static final String FAIL_MSG = "FAIL";
    }

    /**
     * redis key
     */
    public static class RedisKey {

        /**
         * 用于认证授权版本号
         */
        public static final String USER_AUTH_KEY = "SYS:AUTH:USER_AUTH_VERSION:";
    }


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


        /**
         * 无需token校验的接口
         */
        public static final String PERMIT_ALL_API1 = "/v1/api/auth";
        public static final String PERMIT_ALL_API2 = "/v1/api/register";
        public static final String PERMIT_ALL_API10 = "/v1/api/auth/error";
        public static final String PERMIT_ALL_API20 = "/error";

        // 开发放swagger
        public static final String PERMIT_ALL_SWAGGER_API0 = "**/swagger*/**";
        public static final String PERMIT_ALL_SWAGGER_API1 = "/v2/api-docs";
        public static final String PERMIT_ALL_SWAGGER_API2 = "/webjars/springfox-swagger-ui/**/**";


        /**
         * 无需权限的接口
         */
        public static final Set<String> PERMIT_ALL_API = new HashSet<String>() {
            {
                add(PERMIT_ALL_API1);
                add(PERMIT_ALL_API2);
                add(PERMIT_ALL_API10);
                add(PERMIT_ALL_API20);

                add(PERMIT_ALL_SWAGGER_API0);
                add(PERMIT_ALL_SWAGGER_API1);
                add(PERMIT_ALL_SWAGGER_API2);
            }
        };

        /**
         * 无需校验token
         */
        public static final Set<String> NO_CHECK_API = new HashSet<String>() {
            {
                add(PERMIT_ALL_API1);
                add(PERMIT_ALL_API2);
                add(PERMIT_ALL_API10);
                add(PERMIT_ALL_API20);

            }
        };

        /**
         * 认证失败处理接口
         */
        public static final String AUTH_ERROR_API = PERMIT_ALL_API10 + "?msg=";


    }

}
