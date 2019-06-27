package com.boot.springbootelastic.controller;

import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @RequestMapping("/get")
  public List<Employee> get() {

    return employeeService.findByFirstName("xx");
  }
}