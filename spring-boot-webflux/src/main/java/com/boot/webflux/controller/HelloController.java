package com.boot.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * HelloController.java 测试控制层
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/08
 **/
@RestController
public class HelloController {

  @GetMapping("/hello")
  public Mono<String> hello(){
    return Mono.just("Welcome to reactive world ~");
  }
}