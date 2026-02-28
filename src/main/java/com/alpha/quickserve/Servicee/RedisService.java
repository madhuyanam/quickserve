//package com.alpha.quickserve.Servicee;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class RedisService {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    public void save(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    public String get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public void delete(String key) {
//        redisTemplate.delete(key);
//    }
//
//    public void saveWithExpiry(String key, String value, long seconds) {
//        redisTemplate.opsForValue()
//                     .set(key, value, seconds, TimeUnit.SECONDS);
//    }
//}