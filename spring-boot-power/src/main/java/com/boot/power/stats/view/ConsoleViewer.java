package com.boot.power.stats.view;

import com.boot.power.stats.RequestStat;
import com.google.gson.Gson;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: 控制台输出
 * @Date 2020-7-14
 **/
public class ConsoleViewer implements StatViewer {

  @Override
  public void output(Map<String, RequestStat> requestStats, long startTimeInMillis,
      long endTimeInMillis) {
    System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
    Gson gson = new Gson();
    System.out.println(gson.toJson(requestStats));
  }
}
