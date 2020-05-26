package com.boot.power.stats;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author zhaoxiaoping
 * @Description: 数据采集接口
 * @Date 2020-5-26
 **/
public class MetricsCollector {

  private MetricsStorage metricsStorage;

  public MetricsCollector(MetricsStorage metricsStorage) {
    this.metricsStorage = metricsStorage;
  }

  public void recordRequest(RequestInfo requestInfo) {
    if (ObjectUtil.isNull(requestInfo) || StrUtil.isBlank(requestInfo.getApiName())) {
      return;
    }
    metricsStorage.saveRequestInfo(requestInfo);
  }
}
