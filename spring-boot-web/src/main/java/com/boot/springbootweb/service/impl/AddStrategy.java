package com.boot.springbootweb.service.impl;

import com.boot.springbootweb.service.CalStrategy;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 加法
 * @Date 2020/5/6
 **/
@Component
public class AddStrategy implements CalStrategy {

  @Override
  public int calculate(int num1, int num2) {
    return num1 + num2;
  }
}
