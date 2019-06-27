package com.boot.springbootelastic.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Employee.java es 映射对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/27
 **/
@Document(indexName = "megacorp", type = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

  private String firstName;
  private String lastName;
  private Integer age;
  private String about;
  private List<String> interests;
}