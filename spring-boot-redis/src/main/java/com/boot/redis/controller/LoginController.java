package com.boot.redis.controller;

import com.boot.redis.common.beans.ResultBean;
import com.boot.redis.common.beans.SystemConstants.Auth;
import com.boot.redis.entity.UserEntity;
import com.boot.redis.utils.JwtUtil;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2020/3/6
 **/
@RestController
public class LoginController {

  @Autowired
  private RedisTemplate redisTemplate;

  @PostMapping("/login")
  public ResultBean login(UserEntity user) {
    String token = JwtUtil.sign(user.getUsername());
    redisTemplate.opsForValue().set(token, user.getUsername(), Auth.EXPIRE_TIME, TimeUnit.MINUTES);
    return new ResultBean(token);
  }

  public static void main(String[] args) {
    String str = "{id=1}";
  }
}
