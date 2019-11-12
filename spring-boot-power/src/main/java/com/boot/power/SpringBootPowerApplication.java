package com.boot.power;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boot.power.mapper")
public class SpringBootPowerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootPowerApplication.class, args);
  }

}
