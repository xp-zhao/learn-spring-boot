package com.boot.springbootweb;

import com.boot.springbootweb.controller.WebController;
import com.boot.springbootweb.entity.User;
import com.boot.springbootweb.filter.JsonFilter;
import com.boot.springbootweb.service.TestInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
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
  private WebController webController;

  @Autowired
  @Qualifier("test2")
  private TestInterface test;

  @Test
  public void contextLoads() {
  }

  @Test
  public void test() {
    System.out.println(test.getName());
  }

  @Test
  public void testWeb() throws JsonProcessingException {
    for (User user : webController.getData()) {
      System.out.println(JsonFilter.setupJsonFilter().writeValueAsString(user));
    }
  }

}
