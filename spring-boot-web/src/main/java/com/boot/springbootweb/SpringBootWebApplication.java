package com.boot.springbootweb;

import com.boot.springbootweb.filter.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebApplication.class, args);
  }

  /**
   * 跨域过滤器
   */
  @Bean
  public CorsFilter corsFilter() {
    return new CorsFilter();
  }

}
