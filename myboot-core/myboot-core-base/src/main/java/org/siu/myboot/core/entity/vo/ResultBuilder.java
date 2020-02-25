package org.siu.myboot.core.entity.vo;

import org.siu.myboot.core.constant.ResultConstant;
import org.siu.myboot.core.entity.vo.Result;

/**
 * @Author Siu
 * @Date 2020/2/23 17:07
 * @Version 0.0.1
 */
public class ResultBuilder {

    public static Result nullResult(Object resultObj, Object defaultResult) {
        return new Result().setData(null == resultObj ? defaultResult : resultObj).setCode(ResultConstant.SUCCESS).setMessage(ResultConstant.EMPTY_STRING);
    }

    public static Result nullResult(Object defaultResult) {
        return new Result().setData(defaultResult).setCode(ResultConstant.SUCCESS).setMessage(ResultConstant.EMPTY_STRING);
    }
}
