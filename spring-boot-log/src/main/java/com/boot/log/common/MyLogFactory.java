package com.boot.log.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyLogFactory.java 日志工厂
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
public class MyLogFactory {

  public static MyLogger getLogger(Class<?> clazz) {
    Logger logger = LoggerFactory.getLogger(clazz);
    MyLogger myLogger = new MyLogger(logger);
    return myLogger;
  }
}