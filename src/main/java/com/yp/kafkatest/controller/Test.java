package com.yp.kafkatest.controller;


import com.yp.kafkatest.annotation.YP;

@YP
public class Test implements People{

    public Test() {
    }

    @Override
    public String getName(int id) {
        System.out.println("-----get name-----");
        sayHello("yin peng");
        return "tom";
    }

    @Override
    public String sayHello(String name) {
        System.out.println("------say hello------" + name);
        return "kawayi";
    }
}
