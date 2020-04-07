package com.boot.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: redis 测试
 * @Date 2020/3/6
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {
  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void setValue(){
    redisTemplate.opsForValue().set("key", "value");
    System.out.println(redisTemplate.opsForValue().get("key"));
  }
  
  @Test
  public void testList(){
    redisTemplate.opsForZSet().add("bind", "judge", 1);
    redisTemplate.opsForZSet().add("bind", "judge", 2);
    redisTemplate.opsForZSet().add("bind", "judge", 3);
  }
}