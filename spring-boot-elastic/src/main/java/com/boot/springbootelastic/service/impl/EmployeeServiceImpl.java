package com.boot.springbootelastic.service.impl;

import com.boot.springbootelastic.dao.EmployeeRepository;
import com.boot.springbootelastic.entity.Employee;
import com.boot.springbootelastic.service.EmployeeService;
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
  public Page<Employee> findByLastName(String lastName) {
    return employeeRepository.findByLastName(lastName);
  }

  @Override
  public Page<Employee> findByFirstName(String firstName) {
    return employeeRepository.findByFirstName(firstName);
  }

  @Override
  public Page<Employee> findByAbout(String about) {
    return employeeRepository.findByAbout(about);
  }
}