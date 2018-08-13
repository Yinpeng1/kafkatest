package com.yp.kafkatest.controller.base;

import com.yp.kafkatest.Result.Result;
import com.yp.kafkatest.Result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseController {
    /**
     * 其他异常信息捕获处理
     *
     * @param e exception
     * @return 错误信息
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        return Results.errorResult(
                1101110,
                "系统错误"
        );
    }
}
