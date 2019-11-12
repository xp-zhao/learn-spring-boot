package com.boot.power.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserInfoServiceTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {

  @Autowired
  private UserInfoService userinfoService;

  @Test
  public void testUserList(){
    System.out.println(userinfoService.list());
  }
}