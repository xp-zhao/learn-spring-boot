package com.boot.log.web;

import com.boot.log.annotaion.LogAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebController.java 日志测试控制层
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
@RestController
public class WebController {

  @RequestMapping("/log")
  @LogAnnotation
  public String logTest(){
    return get("2013", "xp");
  }

  public String get(String id,String name){
    return id +"-"+ name;
  }
}