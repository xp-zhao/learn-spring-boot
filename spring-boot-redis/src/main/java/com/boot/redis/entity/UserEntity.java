package com.boot.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoxiaoping
 * @Description: 用户对象实体
 * @Date 2020/3/6
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  private String username;
  private String password;
}
