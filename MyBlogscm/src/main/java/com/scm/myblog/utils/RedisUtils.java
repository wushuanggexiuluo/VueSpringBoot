package com.scm.myblog.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class RedisUtils {

    private static RedisTemplate<String,String> redis;
    @Autowired
    private RedisTemplate<String, String> redis1;

    @PostConstruct
    public void init() {
        redis = redis1;
    }
    public static <T>T getDataFromRedis(String s,Class<T> cs) {
        String s1 = redis.opsForValue().get(s);
        if (s1 != null) {
            return JSON.parseObject(s1,cs);
        }
        return null;
    }

    public static void deleteDataFromRedis(String id) {
        redis.delete(id);
    }


    public static void setDataFromRedis(String x, Object y) {
        if (!Objects.isNull(y)) {
            String jsonString = JSON.toJSONString(y);
            redis.opsForValue().set(x, jsonString);
        }
    }
}
