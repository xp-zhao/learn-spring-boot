package com.boot.springbootweb.controller;

import com.boot.springbootweb.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;
import java.util.List;
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

  @RequestMapping("/getData")
  @JsonFormat()
  public List<User> getData() {
    return Arrays.asList(new User("2016-05-02", "xp1", "chengdu"),
        new User("2016-05-02", "xp2", "chengdu"),
        new User("2016-05-02", "xp3", "chengdu"),
        new User("2016-05-02", "xp4", "chengdu"));
  }

}