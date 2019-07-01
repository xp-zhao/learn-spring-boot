package com.boot.springbootelastic;

import com.boot.springbootelastic.dao.EmployeeRepository;
import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticApplicationTests {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository repository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void save() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setAge(23);
    employee.setFirstName("xp");
    employee.setLastName("hj");
    employee.setAbout("aboutTest");
    employee.setInterests(Arrays.asList("music", "food"));
    System.out.println(employeeService.save(employee).toString());
  }

  @Test
  public void findAll() {
    List<Employee> employees = employeeService.findAll();
    employees.forEach(System.out::println);
  }

  @Test
  public void findByListName() {
    employeeService.findByLastName("hj").stream().forEach(System.out::println);
  }

  @Test
  public void findByLastNameLike(){
//    employeeService.findByLastNameLike("j").stream().forEach(System.out::println);
    repository.findByLastNameEndingWith("j").stream().forEach(System.out::println);
  }

  @Test
  public void search(){
    QueryBuilder builder = new QueryStringQueryBuilder("id=1");
    System.out.println("查询语句：" + builder);
    repository.search(builder).forEach(System.out::println);
  }
}
