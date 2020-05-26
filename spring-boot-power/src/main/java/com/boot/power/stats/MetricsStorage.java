package com.boot.power.stats;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: 数据存储
 * @Date 2020-5-26
 **/
public interface MetricsStorage {

  /**
   * 保存请求信息
   *
   * @param requestInfo
   */
  void saveRequestInfo(RequestInfo requestInfo);

  /**
   * 获取请求信息
   *
   * @param apiName
   * @param startTimeInMillis
   * @param endTimeInMillis
   * @return
   */
  List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

  /**
   * 获取请求信息
   *
   * @param startTimeInMillis
   * @param endTimeInMillis
   * @return
   */
  Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
