package com.yp.kafkatest.aop;

import com.alibaba.fastjson.JSON;
import com.yp.kafkatest.Entity.LogEntity;
import com.yp.kafkatest.Result.Result;
import com.yp.kafkatest.utils.GetIpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    private Tracer tracer;

    @Pointcut("execution(public * com.yp.kafkatest.controller..*.*(..))")
    public void controllerAspect() {
    }

//    @Before("controllerAspect()")
//    public void doBeforeAdvice(JoinPoint joinPoint){
//        System.out.println("我是前置通知!!!");
//        //获取目标方法的参数信息
//        Object[] obj = joinPoint.getArgs();
//        //AOP代理类的信息
//        joinPoint.getThis();
//        //代理的目标对象
//        joinPoint.getTarget();
//        //用的最多 通知的签名
//        Signature signature = joinPoint.getSignature();
//        //代理的是哪一个方法
//        System.out.println(signature.getName());
//        //AOP代理类的名字
//        System.out.println(signature.getDeclaringTypeName());
//        //AOP代理类的类（class）信息
//        signature.getDeclaringType();
//        //获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        //如果要获取Session信息的话，可以这样写：
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String,String> parameterMap = new HashMap<>();
//        while (enumeration.hasMoreElements()){
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter,request.getParameter(parameter));
//        }
//        String str = JSON.toJSONString(parameterMap);
//        if(obj.length > 0) {
//            System.out.println("请求的参数信息为："+str);
//        }

    @Before("controllerAspect()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        LocalDateTime time = LocalDateTime.now();
        Signature signature = joinPoint.getSignature();
        String reqIp = GetIpUtils.getUserRealIpAddr(request);
        String reqHeaders = "";
        String reqMethod = signature.getName();
        String className = signature.getDeclaringTypeName();
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<>();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String reqArgs = JSON.toJSONString(parameterMap);
        new LogEntity.LogEntityBuild().time(time).className(className).reqArgs(reqArgs).reqMethod(reqMethod).reqIp(reqIp).reqHeaders(reqHeaders).build();
    }

    @AfterReturning(value = "controllerAspect()", returning = "value")
    public void doReturn(Object value) {
        LogEntity logEntity = LogEntity.getInstance();
//        logEntity.setTraceId(tracer.getCurrentSpan().traceIdString());
        if (ObjectUtils.isEmpty(value)) {
            logEntity.setSuccess(false);
            logEntity.setResult(null);
            return;
        }
        Result result = (Result) value;
        if (result.getCode() != 0 && !StringUtils.isEmpty(result.getMessage())){
            logEntity.setSuccess(false);
        } else {
            logEntity.setSuccess(true);
        }
        logEntity.setResult(value.toString());
        System.out.println(JSON.toJSONString(logEntity));
        kafkaTemplate.send("Log","koala", JSON.toJSONString(logEntity));
    }

//    @AfterThrowing(value = "controllerAspect()",throwing = "exception")
//    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception) {
//        //目标方法名：
//        System.out.println(joinPoint.getSignature().getName());
//    }
}
