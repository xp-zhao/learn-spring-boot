package com.boot.redis.controller;

import com.boot.redis.common.beans.ResultBean;
import com.boot.redis.entity.UserEntity;
import com.boot.redis.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2020/3/6
 **/
@RestController
public class LoginController {

  @PostMapping("/login")
  public ResultBean login(UserEntity user) {
    String token = JwtUtil.sign(user.getUsername());
    return new ResultBean(token);
  }
}
