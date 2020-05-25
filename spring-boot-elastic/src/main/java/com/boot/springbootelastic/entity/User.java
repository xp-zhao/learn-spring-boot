package com.boot.springbootelastic.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-5-25
 **/
@Data
@Builder
public class User {

  private String name;
  private Integer age;
}
