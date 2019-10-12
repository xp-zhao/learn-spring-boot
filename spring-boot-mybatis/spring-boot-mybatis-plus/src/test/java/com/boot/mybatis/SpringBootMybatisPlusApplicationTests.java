package com.boot.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.entity.Order;
import com.boot.mybatis.mapper.EmployeeMapper;
import com.boot.mybatis.mapper.OrderMapper;
import com.boot.mybatis.service.OrderService;
import java.io.UnsupportedEncodingException;
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
  @Autowired
  private OrderService orderService;

  @Test
  public void contextLoads() {
  }

  @Test
  public void getAll(){
    List<Employee> all = mapper.selectList(null);
    all.stream().forEach(System.out::println);
  }

  @Test
  public void saveOrder() throws UnsupportedEncodingException {
    Order order = new Order("1236", "除数是两位数、商的个位不是0的笔算除法的算理：（1）借助除数是一位数除法的笔算经验理解除的顺序（2）如果被除数的前两位够除，第一次除得的商和余数都表示几个十，所以这个商要写在十位）（3）如果被除数的前两位够除，商是两位数；被除数的前两位不够除，商是一位数", "sdf", 12, 12);
    System.out.println(orderMapper.insert(order));
//    Order order1 = new Order("1235", "xp", "sdf", 12, 12);
//    Order order2 = new Order("1236", "xp", "sdf", 12, 12);
//    Order order3 = new Order("1237", "xp", "sdf", 12, 12);
//    List list = Arrays.asList(order, order1, order2, order3);
//    System.out.println(orderService.saveBatch(list));
  }

  @Test
  public void saveEmployee(){
    Employee employee = new Employee();
    employee.setGender(11);
    employee.setAge(22);
    employee.setEmail("sadf");
    employee.setLastName("xp");
    System.out.println(mapper.insert(employee));
  }

  @Test
  public void updateTest(){
    Employee employee = new Employee();
//    employee.setId(1);
    employee.setGender(36);
//    System.out.println(mapper.updateById(employee));
    System.out.println(mapper.update(employee, new QueryWrapper<Employee>().eq("id", 2)));
  }
}
