package com.boot.springbootweb;

import com.boot.springbootweb.service.TestInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWebApplicationTests {

  @Autowired
  @Qualifier("test2")
  private TestInterface test;

  @Test
  public void contextLoads() {
  }

  @Test
  public void test(){
    System.out.println(test.getName());
  }

}
