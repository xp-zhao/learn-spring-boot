package com.boot.springbootelastic.dao;

import com.boot.springbootelastic.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * EmployeeRepository.java dao 层接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

  Page<Employee> findByLastName(String lastName);

  Page<Employee> findByFirstName(String firstName);

  Page<Employee> findByAbout(String about);
}