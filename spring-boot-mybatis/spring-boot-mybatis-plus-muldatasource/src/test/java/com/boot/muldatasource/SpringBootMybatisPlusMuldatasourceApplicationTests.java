package com.boot.muldatasource;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.boot.muldatasource.mapper")
class SpringBootMybatisPlusMuldatasourceApplicationTests {

  @Test
  void contextLoads() {
  }

}
