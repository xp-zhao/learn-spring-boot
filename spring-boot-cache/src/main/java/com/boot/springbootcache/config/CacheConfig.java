package com.boot.springbootcache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @description: 缓存配置
 * @author: zhaoxiaoping
 * @create: 2019/06/24
 **/
@Configuration
@EnableCaching
public class CacheConfig {

  // 默认缓存最大数量
  public static final int DEFAULT_MAXSIZE = 2;
  // 默认过期时间
  public static final int DEFAULT_TTL = 600;

  @Bean
  @Primary
  public CacheManager caffeineCacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    ArrayList<CaffeineCache> caches = new ArrayList<>();
    for (Caches cache : Caches.values()) {
      caches.add(new CaffeineCache(cache.name(),
          Caffeine.newBuilder()
              .recordStats()
              .expireAfterWrite(cache.getTtl(), TimeUnit.SECONDS)
              .maximumSize(cache.getMaxSize())
              .build()));
    }
    cacheManager.setCaches(caches);
    return cacheManager;
  }

  /**
   * 定义cache名称、超时时长（秒）、最大容量
   */
  public enum Caches {
    // 产品名称缓存
    name(600);

    Caches() {
    }

    Caches(int ttl) {
      this.ttl = ttl;
    }

    Caches(int ttl, int maxSize) {
      this.ttl = ttl;
      this.maxSize = maxSize;
    }

    // 最大数量
    private int maxSize = DEFAULT_MAXSIZE;
    // 过期时间（s）
    private int ttl = DEFAULT_TTL;

    public int getMaxSize() {
      return maxSize;
    }

    public int getTtl() {
      return ttl;
    }
  }
}