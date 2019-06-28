package com.boot.springbootelastic.controller;

import com.boot.springbootelastic.dao.EmployeeRepository;
import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.apache.lucene.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ElasticController.java elastic 控制层测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
@RestController
public class ElasticController {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository employeeDao;

  @RequestMapping("/get")
  public List<Employee> get() {
    String json = "{\"query\":{\"match_all\":{}}}";
    return employeeService.findByFirstName("h");
  }

  @RequestMapping("/save")
  public void save(){
    Employee employee = new Employee(112,"h", "j", 222,"about", Arrays.asList("music"));
    Employee result = employeeDao.save(employee);
    System.out.println(result);
  }
}