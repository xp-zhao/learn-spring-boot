package com.boot.mybatis;

import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.entity.Order;
import com.boot.mybatis.mapper.EmployeeMapper;
import com.boot.mybatis.mapper.OrderMapper;
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
  @Autowired
  private OrderMapper orderMapper;

  @Test
  public void contextLoads() {
  }

  @Test
  public void getAll(){
    List<Employee> all = mapper.selectList(null);
    all.stream().forEach(System.out::println);
  }

  @Test
  public void saveOrder(){
    Order order = new Order("1234", "xp", "sdf", 12, 12);
    System.out.println(orderMapper.insert(order));
  }
}
