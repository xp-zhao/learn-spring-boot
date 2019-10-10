package com.boot.springbootweb.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User.java 用户对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("non-address")
public class User {

  private String date;
  private String name;
  private String address;
}