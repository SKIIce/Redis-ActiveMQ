package com.sk.redis.example.controller;

import com.alibaba.fastjson.JSON;
import com.sk.redis.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisClient;
    private RedisTemplate<String,?> redisTemplate;

    @PostMapping("/addItem")
    public String addItem(@RequestBody User user){
        redisClient.opsForValue().set(user.getName(),JSON.toJSONString(user));
        return "success";
    }

    @GetMapping("/getName")
    public String getName(String name){
        redisClient.opsForSet().add("liming", "jimy", "tomy");
        redisClient.opsForSet().add("mini","jimy","kitty");
        Set<String> liming = redisClient.opsForSet().members("liming");
        Set<String> mini = redisClient.opsForSet().members("mini");

        redisClient.opsForSet().union("liming","mini");
        redisClient.opsForSet().intersect("liming","mini");
        return JSON.parseObject(redisClient.opsForValue().get(name),User.class).toString();
    }

    @GetMapping("/addNum")
    public int addNum(String name, int a,int b){
        return JSON.parseObject(redisClient.opsForValue().get(name),User.class).add(a,b);
    }
}
