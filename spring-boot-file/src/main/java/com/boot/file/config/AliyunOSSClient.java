package com.boot.file.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: 阿里云OSS client
 * @Date 2020-8-14
 **/
@Configuration
public class AliyunOSSClient {

  @Autowired
  private AliyunOSSConfig ossConfig;

  @Bean
  public OSS ossClient() {
    ossConfig.setAccessKeyId("xxx");
    ossConfig.setAccessKeySecret("xxx");
    return new OSSClientBuilder()
        .build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }
}
