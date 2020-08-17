package com.boot.file.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.boot.file.config.AliyunOSSClient;
import com.boot.file.config.AliyunOSSConfig;
import java.net.URL;
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
  @Autowired
  private OSS oss;
  @Autowired
  private AliyunOSSClient ossClient;
  
  @Test
  public void testConfig(){
    System.out.println(ossConfig.getAccessKeyId());
  }

  @Test
  public void testAccessUrl(){
    GeneratePresignedUrlRequest request;
    request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), "sssss.png");
    URL url = oss.generatePresignedUrl(request);
    GetObjectRequest objectRequest = new GetObjectRequest(ossConfig.getBucketName(), "sssss.png");
    OSSObject object = oss.getObject(objectRequest);
    System.out.println(url.toString());
  }
}
