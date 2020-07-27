package com.boot.power.stats.stat;

import com.boot.power.stats.RequestInfo;
import com.boot.power.stats.RequestStat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalDouble;

/**
 * @author zhaoxiaoping
 * @Description: 统计数据计算
 * @Date 2020-5-26
 **/
public class Aggregator {

  public Map<String, RequestStat> aggregate(
      Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
    Map<String, RequestStat> requestStats = new HashMap<>();
    for (Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
      String apiName = entry.getKey();
      List<RequestInfo> requestInfoPerApi = entry.getValue();
      RequestStat requestStat = doAggregate(requestInfoPerApi, durationInMillis);
      requestStats.put(apiName, requestStat);
    }
    return requestStats;
  }

  private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
    List<Double> respTimes = new ArrayList<>();
    for (RequestInfo requestInfo : requestInfos) {
      double responseTime = requestInfo.getResponseTime();
      respTimes.add(responseTime);
    }
    RequestStat requestStat = new RequestStat();
    requestStat.setMaxResponseTime(max(respTimes));
    requestStat.setMinResponseTime(min(respTimes));
    requestStat.setAvgResponseTime(avg(respTimes));
    requestStat.setP999ResponseTime(percentile999(respTimes));
    requestStat.setP99ResponseTime(percentile99(respTimes));
    requestStat.setCount(respTimes.size());
    requestStat.setTps((long) tps(respTimes.size(), durationInMillis));
    return requestStat;
  }

  private double max(List<Double> dataset) {
    OptionalDouble max = dataset.stream().mapToDouble(Double::doubleValue).max();
    return max.orElse(0.0D);
  }

  private double min(List<Double> dataset) {
    OptionalDouble min = dataset.stream().mapToDouble(Double::doubleValue).min();
    return min.orElse(0.0D);
  }

  private double avg(List<Double> dataset) {
    OptionalDouble average = dataset.stream().mapToDouble(Double::doubleValue).average();
    return average.orElse(0.0D);
  }

  private double tps(int count, double duration) {
    double tps = count / duration * 1000;
    return tps;
  }

  private double percentile999(List<Double> dataset) {
    return 0.0D;
  }

  private double percentile99(List<Double> dataset) {
    return 0.0D;
  }

  private double percentile(List<Double> dataset, double ratio) {
    return 0.0D;
  }
}
