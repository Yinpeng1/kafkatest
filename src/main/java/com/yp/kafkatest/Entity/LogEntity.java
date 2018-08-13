package com.yp.kafkatest.Entity;

import java.time.LocalDateTime;

public class LogEntity {

    private static final ThreadLocal<LogEntity> logEntityThreadLocal = new ThreadLocal<>();

    //请求是否成功
    private boolean success;
    //请求时间
    private LocalDateTime time;
    //请求参数
    private String reqArgs;
    //请求类名
    private String className;
    //请求方法
    private String reqMethod;
    //traceId(系统)
    private String traceId;
    //请求客户端的ip
    private String reqIp;
    //请求头
    private String reqHeaders;
    //需要打印的一些信息
    private String info;
    //异常错误信息
    private String error;
    //返回结果
    private String result;

    public LogEntity (LogEntityBuild logEntityBuild){
        this.success = logEntityBuild.success;
        this.time = logEntityBuild.time;
        this.reqArgs = logEntityBuild.reqArgs;
        this.className = logEntityBuild.className;
        this.reqMethod = logEntityBuild.reqMethod;
        this.traceId = logEntityBuild.traceId;
        this.reqIp = logEntityBuild.reqIp;
        this.reqHeaders = logEntityBuild.reqHeaders;
        this.info = logEntityBuild.info;
        this.error = logEntityBuild.error;
        this.result = logEntityBuild.result;
        setContext(this);
    }

    private void setContext(LogEntity logEntity) {
        logEntityThreadLocal.set(logEntity);
    }

    public static LogEntity getInstance() {
        return logEntityThreadLocal.get();
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReqArgs() {
        return reqArgs;
    }

    public void setReqArgs(String reqArgs) {
        this.reqArgs = reqArgs;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(String reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class LogEntityBuild{
        private boolean success;
        private LocalDateTime time;
        private String reqArgs;
        private String className;
        private String reqMethod;
        private String traceId;
        private String reqIp;
        private String reqHeaders;
        private String info;
        private String error;
        private String result;

        public LogEntityBuild success(boolean success){
            this.success = success;
            return this;
        }
        public LogEntityBuild time(LocalDateTime time){
            this.time = time;
            return this;
        }
        public LogEntityBuild reqArgs(String reqArgs){
            this.reqArgs = reqArgs;
            return this;
        }
        public LogEntityBuild className(String className){
            this.className = className;
            return this;
        }
        public LogEntityBuild reqMethod(String reqMethod){
            this.reqMethod = reqMethod;
            return this;
        }
        public LogEntityBuild traceId(String traceId){
            this.traceId = traceId;
            return this;
        }
        public LogEntityBuild reqIp(String reqIp){
            this.reqIp = reqIp;
            return this;
        }
        public LogEntityBuild reqHeaders(String reqHeaders){
            this.reqHeaders = reqHeaders;
            return this;
        }
        public LogEntityBuild info(String info){
            this.info = info;
            return this;
        }
        public LogEntityBuild error(String error){
            this.error = error;
            return this;
        }
        public LogEntityBuild result(String result){
            this.result = result;
            return this;
        }

        public LogEntity build(){
            return new LogEntity(this);
        }
    }
}
