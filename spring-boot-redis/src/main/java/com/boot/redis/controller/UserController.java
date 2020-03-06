package com.boot.redis.controller;

import com.boot.redis.annotation.LoginUser;
import com.boot.redis.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiaoping
 * @Description: 用户控制器
 * @Date 2020/3/6
 **/
@RestController
public class UserController {

  @GetMapping("/getUser")
  public UserEntity getUser(@LoginUser UserEntity user) {
    return UserEntity.builder().username(user.getUsername()).build();
  }
}
