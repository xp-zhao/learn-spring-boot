package com.boot.log.format;

import com.alibaba.fastjson.JSON;
import com.boot.log.common.LogThreadLocal;
import com.boot.log.utils.HostUtil;
import lombok.Data;

/**
 * SimpleLog.java 简单日志信息
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
@Data
public class SimpleLog implements LogFormater {

  /**
   * 日志类型
   */
  private String logType;
  /**
   * 日志 id
   */
  private String logId;
  /**
   * 机器 ip
   */
  private String ip;

  private String methodName;
  private Object args;
  private Object result;
  private long du;

  public SimpleLog(String logType) {
    this.logType = logType;
    ip = HostUtil.localIp();
    logId = LogThreadLocal.logId.get();
  }


  @Override
  public String format() {
    return String.format("logId:%s||IP:%s||type:%s||med:%s||req:%s||resp:%s||du:%d" , this.getLogId() ,
        this.getIp() , this.getLogType() , this.getMethodName() , JSON.toJSONString(this.getArgs()) ,
        JSON.toJSONString(this.getResult()) , this.getDu());
  }
}