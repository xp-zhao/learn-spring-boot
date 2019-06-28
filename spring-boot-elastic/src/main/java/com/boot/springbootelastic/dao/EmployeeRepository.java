package com.boot.springbootelastic.dao;

import com.boot.springbootelastic.entity.Employee;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * EmployeeRepository.java dao 层接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {

  List<Employee> findByLastName(String lastName);

  List<Employee> findByFirstName(String firstName);

  List<Employee> findByAbout(String about);
}