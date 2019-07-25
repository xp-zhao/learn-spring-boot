package com.boot.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootNeo4jApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootNeo4jApplication.class, args);
  }

  /**
   * 跨域过滤器
   */
  @Bean
  public com.boot.springbootweb.filter.CorsFilter corsFilter() {
    return new com.boot.springbootweb.filter.CorsFilter();
  }

}
