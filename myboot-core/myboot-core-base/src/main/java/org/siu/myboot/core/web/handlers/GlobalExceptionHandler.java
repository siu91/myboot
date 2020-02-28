package org.siu.myboot.core.web.handlers;

import lombok.extern.slf4j.Slf4j;
import org.siu.myboot.core.entity.vo.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new Result().error(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
