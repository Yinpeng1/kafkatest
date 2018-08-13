package com.yp.kafkatest.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results {

    private Results() {
    }

//    public static <T> Result listResult(List<T> entities) {
//        return createResult(new ListData<>(entities));
//    }
//
//    public static <T> Result singleResult(T entity) {
//        return createResult(new SingleData<>(entity));
//    }

//    public static <T> Result pageResult(Long total, List<T> entities) {
//        return createResult(new PageData<>(total, entities));
//    }

    public static Result kvResult(String key, Object value) {
        Map<String, Object> data = new HashMap<>();
        data.put(key, value);

        return createResult(data);
    }

    public static Result customResult(Object data) {
        return createResult(data);
    }

    public static Result emptyResult() {
        return new Result();
    }

    public static Result errorResult(long code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);

        return result;
    }

    private static Result createResult(Object data) {
        Result result = new Result();
        result.setData(data);

        return result;
    }
}
