package com.boot.muldatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lenovo
 */
@SpringBootApplication
@MapperScan("com.boot.muldatasource.mapper")
public class SpringBootMybatisPlusMuldatasourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootMybatisPlusMuldatasourceApplication.class, args);
  }

}
