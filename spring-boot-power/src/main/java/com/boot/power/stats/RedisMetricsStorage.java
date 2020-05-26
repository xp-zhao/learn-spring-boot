package com.boot.power.stats;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: RedisMetricsStorage
 * @Date 2020-5-26
 **/
public class RedisMetricsStorage implements MetricsStorage {

  @Override
  public void saveRequestInfo(RequestInfo requestInfo) {
    System.out.println("保存数据到 redis");
  }

  @Override
  public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis,
      long endTimeInMillis) {
    return Collections.emptyList();
  }

  @Override
  public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis,
      long endTimeInMillis) {
    return Collections.emptyMap();
  }
}
