package com.boot.mybatis;

import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.mapper.EmployeeMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {

  @Autowired
  private EmployeeMapper mapper;

  @Test
  public void contextLoads() {
  }

  @Test
  public void getAll(){
    List<Employee> all = mapper.selectList(null);
    all.stream().forEach(System.out::println);
  }
}
