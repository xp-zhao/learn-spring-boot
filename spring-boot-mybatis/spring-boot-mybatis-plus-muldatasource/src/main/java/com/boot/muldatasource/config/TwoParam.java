package com.boot.muldatasource.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OneParam.java
 * 数据库参数
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/18
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource.two")
public class TwoParam {

  private String url;
  private String username;
  private String password;
  private String driverClassName;
}