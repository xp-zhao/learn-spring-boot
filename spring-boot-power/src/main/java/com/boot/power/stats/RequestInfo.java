package com.boot.power.stats;

import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description: 请求信息
 * @Date 2020-5-26
 **/
@Data
public class RequestInfo {

  private String apiName;
  private double responseTime;
  private long timestamp;
}
