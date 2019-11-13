package com.boot.power.config;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfig.java mybatis-plus配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/10/12
 **/
@Configuration
public class MybatisPlusConfig {

  /**
   * value = name = prefix + name
   * havingValue: 比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
   */
  @Bean
  @ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "local")
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    /***
     * <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->
     */
//    performanceInterceptor.setMaxTime(200);
    /***
     * <!--SQL是否格式化 默认false-->
     */
    performanceInterceptor.setFormat(true);
    return performanceInterceptor;
  }
}