package com.boot.springbootweb.entity;

import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 测试
 * @Date 2020/4/1
 **/
@Data
@Component
public class ValueDemo {

  @Value("${yml.value}")
  private String value;

  @PostConstruct
  private void init() {
    value = value + "success";
  }
}
