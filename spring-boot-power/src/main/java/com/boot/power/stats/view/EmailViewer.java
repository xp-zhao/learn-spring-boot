package com.boot.power.stats.view;

import com.boot.power.stats.RequestStat;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: 邮件推送
 * @Date 2020-7-14
 **/
public class EmailViewer implements StatViewer {

  @Override
  public void output(Map<String, RequestStat> requestStats, long startTimeInMillis,
      long endTimeInMillis) {
    
  }
}
