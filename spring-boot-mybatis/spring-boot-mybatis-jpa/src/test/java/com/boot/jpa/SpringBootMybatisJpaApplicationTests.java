package com.boot.jpa;

import com.boot.jpa.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisJpaApplicationTests {

  @Autowired
  private EmployeeRepository repository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void query(){
    System.out.println(repository.findAll());
  }

}
