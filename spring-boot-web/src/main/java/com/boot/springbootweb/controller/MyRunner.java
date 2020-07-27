package com.boot.springbootweb.controller;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-7-20
 **/
@Component
public class MyRunner implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
    TimeUnit.SECONDS.sleep(60);
    System.out.println("加载数据完成");
  }
}
