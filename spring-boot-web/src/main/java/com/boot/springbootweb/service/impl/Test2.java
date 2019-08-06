package com.boot.springbootweb.service.impl;

import com.boot.springbootweb.service.TestInterface;
import org.springframework.stereotype.Service;

/**
 * Test2.java 测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/06
 **/
@Service("test2")
public class Test2 implements TestInterface {

  @Override
  public String getName() {
    return "test2";
  }
}