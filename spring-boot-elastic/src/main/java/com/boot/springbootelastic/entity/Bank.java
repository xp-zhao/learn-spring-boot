package com.boot.springbootelastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Bank.java Bank实体对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/12
 **/
@Document(indexName = "bank", type = "_doc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bank {
  @Id
  private Integer account_number;
  private Integer balance;
  private String firstname;
  private String lastname;
  private Integer age;
  private String gender;
  private String address;
  private String employer;
  private String email;
  private String city;
  private String state;
}