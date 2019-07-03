package com.boot.springbootelastic;

import com.boot.springbootelastic.dao.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BlogTests.java 测试类
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTests {
  @Autowired
  private BlogRepository repository;

  @Test
  public void findAll(){
    repository.findAll().forEach(System.out::println);
  }
}