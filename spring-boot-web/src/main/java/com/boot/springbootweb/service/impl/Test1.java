package com.boot.springbootweb.service.impl;

import com.boot.springbootweb.service.TestInterface;
import org.springframework.stereotype.Service;

/**
 * Test1.java 测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/06
 **/
@Service("test1")
public class Test1 implements TestInterface {

  @Override
  public String getName() {
    return "test1";
  }
}