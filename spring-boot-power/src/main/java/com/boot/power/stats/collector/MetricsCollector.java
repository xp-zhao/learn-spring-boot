package com.boot.power.stats.collector;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.boot.power.stats.RequestInfo;
import com.boot.power.stats.storage.MetricsStorage;

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
