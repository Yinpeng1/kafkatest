package com.yp.kafkatest.Result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    /**
     * 错误码
     */
    private long code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 请求到达时间
     */
    private long begin;

    /**
     * 请求响应时间
     */
    private long end;

    /**
     * 请求唯一id
     */
    private String reqId;

    /**
     * 请求响应数据
     */
    private Object data;
}
