package com.yp.kafkatest.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YP {

    String value() default "hello";
}
