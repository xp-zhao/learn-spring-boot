package com.boot.mybatis.dao;

import com.boot.mybatis.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * dao 层接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Repository
public interface EmployeeDao {

  List<Employee> getAll();

  Employee getOne(Integer id);

  void insert(Employee employee);

  void update(Employee employee);

  void delete(Integer id);
}
