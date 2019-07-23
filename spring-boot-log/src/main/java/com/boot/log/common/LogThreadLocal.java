package com.boot.log.common;

/**
 * LogThreadLocal.java 日志 id 存储
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
public class LogThreadLocal {

  public static final ThreadLocal<String> logId = new ThreadLocal<>();
}