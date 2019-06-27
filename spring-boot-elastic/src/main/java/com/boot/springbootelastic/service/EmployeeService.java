package com.boot.springbootelastic.service;

import com.boot.springbootelastic.entity.Employee;
import java.util.List;

/**
 * service 层
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
public interface EmployeeService {

  List<Employee> findByLastName(String lastName);

  List<Employee> findByFirstName(String firstName);

  List<Employee> findByAbout(String about);
}
