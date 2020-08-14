package com.boot.file.oss;

import com.boot.file.config.AliyunOSSConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 配置测试
 * @Date 2020-8-14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AliyunOSSTest {
  @Autowired
  private AliyunOSSConfig ossConfig;
  
  @Test
  public void testConfig(){
    System.out.println(ossConfig.getAccessKeyId());
  }
}
