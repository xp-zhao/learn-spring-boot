package com.boot.log;

import com.boot.log.annotaion.LogAnnotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootLogApplicationTests {

  @Test
  public void contextLoads() {
    System.out.println(get("2013", "xp"));
  }

  @LogAnnotation
  public String get(String id,String name){
    return id +"-"+ name;
  }

}
