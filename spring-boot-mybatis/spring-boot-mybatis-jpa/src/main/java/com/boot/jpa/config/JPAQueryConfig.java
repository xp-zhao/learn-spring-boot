package com.boot.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date
 **/
@Configuration
public class JPAQueryConfig {

  @Bean
  @Autowired
  public JPAQueryFactory jpaQuery(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }
}
