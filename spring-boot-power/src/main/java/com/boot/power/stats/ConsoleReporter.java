package com.boot.power.stats;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxiaoping
 * @Description: 控制台输出
 * @Date 2020-5-26
 **/
public class ConsoleReporter {
  private MetricsStorage metricsStorage;
  private ScheduledExecutorService executor;

  public ConsoleReporter(MetricsStorage metricsStorage) {
    this.metricsStorage = metricsStorage;
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void startRepeatedReport(long periodInSeconds, long durationInSeconds){
    executor.scheduleAtFixedRate(() -> {
      // 根据指定的时间区间，拉取数据
    }, 0, periodInSeconds, TimeUnit.SECONDS);
  }
}
