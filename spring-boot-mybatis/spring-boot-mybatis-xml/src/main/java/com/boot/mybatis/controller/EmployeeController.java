package com.boot.mybatis.controller;

import com.boot.mybatis.dao.EmployeeDao;
import com.boot.mybatis.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController.java Employee控制层
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@RestController
public class EmployeeController {

  @Autowired
  private EmployeeDao employeeDao;

  @RequestMapping("/getAll")
  public List<Employee> getAll(){
    return employeeDao.getAll();
  }

  @RequestMapping("/getOne/{id}")
  public Employee getOne(@PathVariable Integer id){
    return employeeDao.getOne(id);
  }

  @RequestMapping("/insert")
  public void insert(@ModelAttribute Employee employee){
    employeeDao.insert(employee);
  }

  @RequestMapping("/update")
  public void update(@ModelAttribute Employee employee){
    employeeDao.update(employee);
  }

  @RequestMapping("/delete/{id}")
  public void delete(@PathVariable Integer id){
    employeeDao.delete(id);
  }
}