package com.boot.power.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserinfoServiceTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserinfoServiceTest {

  @Autowired
  private UserinfoService userinfoService;

  @Test
  public void testUserList(){
    System.out.println(userinfoService.list());
  }
}