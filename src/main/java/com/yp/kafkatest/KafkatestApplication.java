package com.yp.kafkatest;

import com.yp.kafkatest.aop.ScanYP;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
public class KafkatestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkatestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new ScanYP().getControllers().forEach(t -> {
            try{
                ScanYP.map.put(t, t.newInstance());
            } catch (Exception e){
                System.out.println("实例化失败");
            }
        });
        new ScanYP().setComings();
    }
}
