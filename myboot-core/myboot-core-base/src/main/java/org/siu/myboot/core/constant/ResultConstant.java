package org.siu.myboot.core.constant;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author Siu
 * @Date 2020/2/23 16:55
 * @Version 0.0.1
 */
public class ResultConstant {

    /**
     * 成功返回码
     */
    public static final int SUCCESS = 0;
    /**
     * 默认失败返回码 未知错误
     */
    public static final int FAIL = -1;
    /**
     * 空json对象
     */
    public static final String EMPTY_OBJECT = "{}";
    public static final String EMPTY_OBJECT_ARR = "[]";
    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";
    /**
     * 空list
     */
    public static final Collection<?> EMPTY_LIST = new ArrayList<>();

    public static final String SUCCESS_MSG = "SUCCESS";
    public static final String FAIL_MSG = "FAIL";

}
