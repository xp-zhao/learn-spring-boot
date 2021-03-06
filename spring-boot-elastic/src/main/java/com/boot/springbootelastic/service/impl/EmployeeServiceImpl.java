package com.boot.springbootelastic.service.impl;

import com.boot.springbootelastic.dao.EmployeeRepository;
import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * EmployeeServiceImpl.java 实现类
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findByLastName(String lastName) {
    return employeeRepository.findByLastName(lastName);
  }

  @Override
  public List<Employee> findByLastNameLike(String lastName) {
    return employeeRepository.findByLastNameLike(lastName);
  }

  @Override
  public List<Employee> findByFirstName(String firstName) {
    return employeeRepository.findByFirstName(firstName);
  }

  @Override
  public List<Employee> findByAbout(String about) {
    return employeeRepository.findByAbout(about);
  }

  @Override
  public List<Employee> findAll(){
    Iterable<Employee> iterable = employeeRepository.findAll();
    List<Employee> result = new ArrayList<>();
    iterable.forEach(item -> result.add(item));
    return result;
  }

  @Override
  public Employee save(Employee employee){
    return employeeRepository.save(employee);
  }
}