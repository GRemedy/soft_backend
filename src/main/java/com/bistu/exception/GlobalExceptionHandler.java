package com.bistu.exception;

import com.bistu.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/7
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @author Gremedy
     * @description
     * @date 2023/6/7 19:38
     * @param de  自定义异常
     * @return Result
     **/

    @ExceptionHandler(DefaultException.class)
    public Result defaultException(DefaultException de){
        return Result.error(de.getErrorCode(), de.getErrorMsg());
    }
    /**
     * @param re 处理所有异常
     * @return Result
     * @author Gremedy
     * @description
     * @date 2023/6/7 18:42
     **/

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException re) {
        return Result.error("0","系统异常，请联系管理员");
    }



}
