package com.boot.power.stats.view;

import com.boot.power.stats.RequestInfo;
import com.boot.power.stats.RequestStat;
import com.boot.power.stats.stat.Aggregator;
import com.boot.power.stats.storage.MetricsStorage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxiaoping
 * @Description: 邮件通知
 * @Date 2020-7-15
 **/
public class EmailReporter {

  public static final Long DAY_HOURS_IN_SECONDS = 24 * 60 * 60L;

  private MetricsStorage metricsStorage;
  private Aggregator aggregator;
  private StatViewer statViewer;
  private ScheduledExecutorService executor;

  public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator,
      StatViewer statViewer) {
    this.metricsStorage = metricsStorage;
    this.aggregator = aggregator;
    this.statViewer = statViewer;
    this.executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void startDailyReport() {
    long periodInSeconds = LocalDateTime.now().plusDays(1L).getSecond();
    executor.scheduleAtFixedRate(() -> {
      // 根据指定的时间区间，拉取数据
      long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
      long endTimeInMillis = System.currentTimeMillis();
      long startTimeInMillis = endTimeInMillis - durationInMillis;
      Map<String, List<RequestInfo>> requestInfos = metricsStorage
          .getRequestInfos(startTimeInMillis, endTimeInMillis);
      Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
      statViewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }, 0, periodInSeconds, TimeUnit.SECONDS);
  }
}
