package com.boot.power.stats.collector;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiaoping
 * @Description: 接口请求统计
 * @Date 2020-5-26
 **/
@Service
public class Metrics {

  /**
   * key: 接口名称， value 对应接口
   */
  private Map<String, List<Double>> responseTimes = new HashMap<>();
  private Map<String, List<Double>> timestamps = new HashMap<>();
  private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  public void recordResponseTime(String apiName, double responseTime) {
    responseTimes.putIfAbsent(apiName, Collections.EMPTY_LIST);
    responseTimes.get(apiName).add(responseTime);
  }

  public void recordTimestamp(String apiName, double timestamp) {
    timestamps.putIfAbsent(apiName, Collections.EMPTY_LIST);
    timestamps.get(apiName).add(timestamp);
  }

  public void startRepeatedReport(long period, TimeUnit unit) {
    executor.scheduleAtFixedRate(() -> {
      Gson gson = new Gson();
      Map<String, Map<String, Double>> stats = new HashMap<>();
      for (Entry<String, List<Double>> entry : responseTimes.entrySet()) {
        String apiName = entry.getKey();
        List<Double> apiRespTimes = entry.getValue();
        stats.putIfAbsent(apiName, Collections.EMPTY_MAP);
        stats.get(apiName).put("max", max(apiRespTimes));
        stats.get(apiName).put("avg", avg(apiRespTimes));
      }

      for (Entry<String, List<Double>> entry : timestamps.entrySet()) {
        String apiName = entry.getKey();
        List<Double> apiTimestamps = entry.getValue();
        stats.putIfAbsent(apiName, Collections.EMPTY_MAP);
        stats.get(apiName).put("count", (double) apiTimestamps.size());
      }
      System.out.println(gson.toJson(stats));
    }, 0, period, unit);
  }

  private double max(List<Double> dataset) {
    return dataset.stream().mapToDouble(Double::new).max().orElse(0);
  }

  private double avg(List<Double> dataset) {
    return dataset.stream().mapToDouble(Double::new).average().orElse(0);
  }
}
