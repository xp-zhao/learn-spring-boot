package com.boot.power.stats;

import com.boot.power.stats.stat.Aggregator;
import com.boot.power.stats.storage.MetricsStorage;
import com.boot.power.stats.storage.RedisMetricsStorage;
import com.boot.power.stats.view.ConsoleReporter;
import com.boot.power.stats.view.ConsoleViewer;

/**
 * @author zhaoxiaoping
 * @Description: 测试
 * @Date 2020-7-17
 **/
public class PerfCounterTest {

  public static void main(String[] args) {
    MetricsStorage storage = new RedisMetricsStorage();
    Aggregator aggregator = new Aggregator();
    
    // 定时触发统计，将结果显示到终端
    ConsoleViewer consoleViewer = new ConsoleViewer();
    ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
    consoleReporter.startRepeatedReport(60, 60);
    // 定时触发统计，将结果显示到邮件
    
  }
}
