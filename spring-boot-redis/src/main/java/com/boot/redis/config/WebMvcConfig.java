package com.boot.redis.config;

import com.boot.redis.common.interceptor.LoginUserHandlerMethodArgumentResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoxiaoping
 * @Description: mvc 配置
 * @Date 2020/3/6
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
  }
}
