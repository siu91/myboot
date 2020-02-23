package org.siu.myboot.core.web.handlers;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.annotation.RestfulApi;
import org.siu.myboot.core.constant.ResultConstant;
import org.siu.myboot.core.restful.result.Result;
import org.siu.myboot.core.restful.result.ResultBuilder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @Author Siu
 * @Date 2020/2/23 16:48
 * @Version 0.0.1
 */
@Slf4j
public class JsonReturnHandler implements HandlerMethodReturnValueHandler {


    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        /** 包含RestfulApi注解时，该处理生效*/
         return null != returnType.getMethod().getDeclaringClass().getAnnotation(RestfulApi.class)
                || null != returnType.getMethodAnnotation(RestfulApi.class);
    }

    @Override
    public void handleReturnValue(Object result, MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        /**  返回值不为空时，把返回值转化为标准的globalResult形式*/
        Result returnResult = null;
        if (null != result) {
            if (result instanceof Result) {
                returnResult = (Result) result;
            } else {
                returnResult = ResultBuilder.nullResult(result, ResultConstant.EMPTY_OBJECT);
            }
        } else {
            Class<?> returnType = Objects.requireNonNull(methodParameter.getMethod()).getReturnType();


            if (returnType.isInstance(ResultConstant.EMPTY_LIST)) {
                returnResult = ResultBuilder.nullResult(ResultConstant.EMPTY_LIST);
            } else if (returnType.isArray()) {
                returnResult = ResultBuilder.nullResult(ResultConstant.EMPTY_LIST);
            } else {
                returnResult = ResultBuilder.nullResult(ResultConstant.EMPTY_OBJECT);
            }
        }
        writeResponse(request, response, returnResult);

    }

    private static void writeResponse(HttpServletRequest request, HttpServletResponse response, Object result) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            log.debug(JSON.toJSONString(result));
            writer.flush();
        } catch (Exception ex) {
            log.error("printWriter", ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}