package com.yp.kafkatest.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy implements InvocationHandler {

    private Object object;

    TestProxy() {
        super();
    }

    TestProxy(Object target) {
        super();
        this.object = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getName".equals(method.getName())){
            System.out.println("++++++before " + method.getName() + "++++++");
            Object result = method.invoke(object, args);
            System.out.println("++++++after " + method.getName() + "++++++");
            return result;
        }else{
            Object result = method.invoke(object, args);
            return result;
        }
    }

    public static void main(String[] args) {
        People test = new Test();
        InvocationHandler invocationHandler = new TestProxy(test);
//        People testProxy = (People) Proxy.newProxyInstance(test.getClass().getClassLoader(), test.getClass().getInterfaces(),invocationHandler);
        People testProxy = (People) Proxy.newProxyInstance(People.class.getClassLoader(), new Class<?>[]{People.class},invocationHandler);
        System.out.println(testProxy.getName(1));
    }
}
