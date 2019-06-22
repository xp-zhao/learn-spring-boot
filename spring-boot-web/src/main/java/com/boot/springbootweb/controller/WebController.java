package com.boot.springbootweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: web控制层
 * @author: zhaoxiaoping
 * @create: 2019/06/22
 **/
@RestController
public class WebController {

  @RequestMapping("/index")
  public String index() {
    return "hello SpringBoot";
  }

}