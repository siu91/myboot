package org.siu.myboot.core.web.handlers;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.vo.Result;
import org.siu.myboot.core.exception.BaseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @Author Siu
 * @Date 2020/2/28 16:38
 * @Version 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法参数校验异常统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new Result().paramsError(e.getBindingResult().getFieldError().getDefaultMessage());
    }


    /**
     * 其它异常统一处理
     *
     * @param req
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {Exception.class, BaseException.class, RuntimeException.class, Throwable.class})
    public Result exceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        Result result = new Result();
        // 除了 内部定义的exception(继承BaseException) 其它都是未知错误
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            // TODO 定以各异常，统一格式返回给前端
            result.innerError(baseException);
        } else {
            result.unknownError(e);
        }

        return result;
    }
}
