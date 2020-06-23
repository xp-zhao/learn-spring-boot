package com.boot.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 日志打印测试
 * @Date 2020-6-5
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

  @Test
  public void testLog() {
    log.info("log test");
  }
}
