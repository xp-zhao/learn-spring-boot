package com.boot.springbootcache.annotation;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 缓存统计
 * @author: zhaoxiaoping
 * @create: 2019/06/24
 **/
@Aspect
@Component
@Slf4j
public class CacheAspect {

  @Before(value = "@annotation(cacheStatistics)")
  public void servicePointcut(Cacheable cacheStatistics)
      throws Throwable {
    log.info("请求日志");
  }
}