package com.boot.neo4j.config;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Neo4jConfig.java neo4j 配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/18
 **/

@Configuration
public class Neo4jConfig {
  @Value("${spring.data.neo4j.uri}")
  private String url;
  @Value("${spring.data.neo4j.username}")
  private String username;
  @Value("${spring.data.neo4j.password}")
  private String password;

  @Bean(name = "driver")
  public Driver initDriver() {
    Driver driver;
    try {
      driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return driver;
  }
}