package com.boot.jpa;

import com.boot.jpa.entity.Employee;
import com.boot.jpa.repository.EmployeeRepository;
import java.util.Optional;
import java.util.concurrent.Executors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  @Test
  public void insert(){
    Employee employee = new Employee();
    employee.setLastName("xp");
    employee.setEmail("1132813751@qq.com");
    employee.setAge(24);
    employee.setGender(222);
    System.out.println(repository.save(employee).toString());
  }

  @Test
  public void findOne(){
    Employee employee = new Employee();
    employee.setLastName("cj");
    Example<Employee> example = Example.of(employee);
    Optional<Employee> result = repository.findOne(example);
    if(result.isPresent()){
      System.out.println(result.toString());
    }else{
      System.out.println("没有符合条件的值");
    }
  }

  @Test
  public void findByLastName(){
    System.out.println(repository.findByLastName("cj").toString());
  }

  @Test
  public void update(){
    System.out.println(repository.update("updateTest", 1));
  }
}
