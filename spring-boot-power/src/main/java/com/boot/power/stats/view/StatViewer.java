package com.boot.power.stats.view;

import com.boot.power.stats.RequestStat;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: 统计数据展示
 * @Date 2020-7-14
 **/
public interface StatViewer {

  /**
   * 输出显示
   *
   * @param requestStats
   * @param startTimeInMillis
   * @param endTimeInMillis
   */
  void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis);
}
