package com.boot.log.common;

import com.boot.log.format.LogFormater;
import org.slf4j.Logger;

/**
 * MyLogger.java 自定义 Logger
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
public class MyLogger {
  private Logger logger;

  protected MyLogger(Logger logger)
  {
    this.logger = logger;
  }

  public void info(LogFormater obj)
  {
    logger.info(obj.format());
  }

  public void info(String msg)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("logid:").append(LogThreadLocal.logId.get()).append("||").append(msg);
    logger.info(sb.toString());
  }

  public void debug(LogFormater obj)
  {
    if(logger.isDebugEnabled())
    {
      logger.debug(obj.format());
    }
  }

  public void debug(String msg)
  {
    if(logger.isDebugEnabled())
    {
      StringBuilder sb = new StringBuilder();
      sb.append("logid:").append(LogThreadLocal.logId.get()).append("||").append(msg);
      logger.debug(sb.toString());
    }
  }

  public void error(String msg)
  {
    if(logger.isErrorEnabled())
    {
      StringBuilder sb = new StringBuilder();
      sb.append("logid:").append(LogThreadLocal.logId.get()).append("||").append(msg);
      logger.error(sb.toString());
    }
  }
}