package com.boot.power.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoxiaoping
 * @Description: 请求信息
 * @Date 2020-5-26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {

  private String apiName;
  private double responseTime;
  private long timestamp;
}
