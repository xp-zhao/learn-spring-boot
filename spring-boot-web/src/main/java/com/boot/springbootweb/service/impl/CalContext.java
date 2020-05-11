package com.boot.springbootweb.service.impl;

import com.boot.springbootweb.service.CalStrategy;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020/5/6
 **/
@Component
public class CalContext {

  private final Map<String, CalStrategy> strategyMap = new ConcurrentHashMap<>();

  @Autowired
  public void initMap(Map<String, CalStrategy> strategyMap) {
    this.strategyMap.clear();
    strategyMap.forEach(this.strategyMap::put);
    System.out.println(this.strategyMap);
  }

  public CalStrategy getStrategy(String type) {
    Preconditions.checkArgument(!StringUtils.isEmpty(type), "类型不能为空");
    return this.strategyMap.get(type);
  }
}
