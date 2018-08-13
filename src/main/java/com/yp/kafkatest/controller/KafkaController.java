package com.yp.kafkatest.controller;


import com.yp.kafkatest.Entity.LogEntity;
import com.yp.kafkatest.Result.Result;
import com.yp.kafkatest.Result.Results;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController{

        @RequestMapping("/kafka")
        public Result testKafka(String name, String age) {
            LogEntity.getInstance().setInfo("你妹");
            System.out.println("name="+name+"age="+age);
            return Results.customResult("你好吗");
        }
}
