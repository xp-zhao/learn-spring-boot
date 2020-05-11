package com.boot.springbootweb;

import com.boot.springbootweb.service.impl.CalContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 策略模式测试
 * @Date 2020/5/6
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStrategy {
  @Autowired
  private CalContext calContext;
  
  @Test
  public void testCal(){
    System.out.println(calContext.getStrategy("addStrategy").calculate(1, 2));
  }
}
