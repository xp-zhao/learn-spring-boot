package com.boot.jpa.repository;

import com.boot.jpa.entity.Employee;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Employee findByLastName(String lastName);

  @Modifying
  @Transactional
  @Query(value = "update Employee e set e.lastName = ?1 where e.id = ?2")
  int update(String lastName, Integer id);
}
