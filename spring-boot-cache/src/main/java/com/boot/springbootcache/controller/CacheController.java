package com.boot.springbootcache.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 本地缓存测试
 * @author: zhaoxiaoping
 * @create: 2019/06/22
 **/
@RestController
public class CacheController {

  @RequestMapping("/getData")
  @Cacheable(value = "name", key = "#name")
  public String getData(@RequestParam("name") String name) {
    System.out.println("没走缓存");
    return "xp";
  }
}