package com.boot.power.stats;

import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description: 请求统计
 * @Date 2020-5-26
 **/
@Data
public class RequestStat {

  private double maxResponseTime;
  private double minResponseTime;
  private double avgResponseTime;
  private double p999ResponseTime;
  private double p99ResponseTime;
  private long count;
  private long tps;
}
