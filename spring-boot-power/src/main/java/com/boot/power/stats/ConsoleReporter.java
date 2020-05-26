package com.boot.power.stats;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

  public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
    executor.scheduleAtFixedRate(() -> {
      // 根据指定的时间区间，拉取数据
      long durationInMillis = durationInSeconds * 1000;
      long endTimeInMillis = System.currentTimeMillis();
      long startTimeInMillis = endTimeInMillis - durationInMillis;
      Map<String, List<RequestInfo>> requestInfos = metricsStorage
          .getRequestInfos(startTimeInMillis, endTimeInMillis);
      Map<String, RequestStat> stats = new HashMap<>();
      for (Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
        String apiName = entry.getKey();
        List<RequestInfo> requestInfosPerApi = entry.getValue();
        // 根据原始数据，计算得到统计数据
        RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
        stats.put(apiName, requestStat);
      }
      // 将统计数据显示到终端
      System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis);
      Gson gson = new Gson();
      System.out.println(gson.toJson(stats));
    }, 0, periodInSeconds, TimeUnit.SECONDS);
  }
}
