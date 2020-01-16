package com.boot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SecurityController.java
 * Security测试控制层
 *
 * @author: zhaoxiaoping
 * @date: 2020/01/16
 **/
@RestController
public class SecurityController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/ignore")
  public String ignore(){
    return "ignore";
  }
}