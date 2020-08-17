package com.boot.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 阿里云OSS配置
 * @Date 2020-8-14
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSConfig {

  private String accessKeyId;
  private String accessKeySecret;
  private String bucketName;
  private String endpoint;
  private String accessHost;
}
