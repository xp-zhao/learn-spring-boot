package com.boot.springbootweb.controller;

import com.boot.springbootweb.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExceptionController.java 全局异常测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@RestController
@RequestMapping("/exception")
public class ExceptionController {

  @GetMapping("/test")
  public String test(Integer num) {
    if (num == null) {
      throw new CustomException(400, "num不能为空");
    }

    int i = 10 / num;
    return "result: " + i;
  }
}