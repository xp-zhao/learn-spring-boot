package com.boot.muldatasource.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfig.java
 * mybatis-plus 通用配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/19
 **/
@Configuration
public class MybatisPlusConfig {

  /**
   * mybatis-plus分页插件<br>
   * 文档：http://mp.baomidou.com<br>
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
    return paginationInterceptor;
  }

  /**
   * value = name = prefix + name
   * havingValue: 比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
   */
  @Bean
  @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    performanceInterceptor.setFormat(true);
    return performanceInterceptor;
  }

  @Bean(name = "plugins")
  public Interceptor[] getPlugins() {
    return new Interceptor[]{paginationInterceptor(), performanceInterceptor()};
  }
}