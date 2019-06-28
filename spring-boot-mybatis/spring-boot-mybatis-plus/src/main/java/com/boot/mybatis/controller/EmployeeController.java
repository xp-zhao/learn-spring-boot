package com.boot.mybatis.controller;

import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.mapper.EmployeeMapper;
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
  private EmployeeMapper employeeMapper;

  @RequestMapping("/getAll")
  public List<Employee> getAll(){
    return employeeMapper.selectList(null);
  }

  @RequestMapping("/getOne/{id}")
  public Employee getOne(@PathVariable Integer id){
    return employeeMapper.selectById(id);
  }

  @RequestMapping("/insert")
  public void insert(@ModelAttribute Employee employee){
    employeeMapper.insert(employee);
  }

  @RequestMapping("/update")
  public void update(@ModelAttribute Employee employee){
    employeeMapper.updateById(employee);
  }

  @RequestMapping("/delete/{id}")
  public void delete(@PathVariable Integer id){
    employeeMapper.deleteById(id);
  }
}