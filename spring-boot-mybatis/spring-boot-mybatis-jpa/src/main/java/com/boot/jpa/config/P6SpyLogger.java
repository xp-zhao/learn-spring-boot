package com.boot.jpa.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoxiaoping
 * @Description: p6spy 日志打印格式
 * @Date 2020/3/16
 **/
public class P6SpyLogger implements MessageFormattingStrategy {

  public P6SpyLogger() {
  }

  @Override
  public String formatMessage(int connectionId, String now, long elapsed, String category,
      String prepared, String sql, String url) {
    return StringUtils.isNotBlank(sql) ? " Consume Time：" + elapsed + " ms " + now
        + "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : "";
  }
}
