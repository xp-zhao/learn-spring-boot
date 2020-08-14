package com.boot.file.service.impl;

import com.aliyun.oss.OSS;
import com.boot.file.config.AliyunOSSConfig;
import com.boot.file.service.FileStore;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaoxiaoping
 * @Description: 阿里云文件存储服务
 * @Date 2020-8-14
 **/
@Service
@Slf4j
public class AliyunFileStore implements FileStore {

  @Autowired
  private AliyunOSSConfig ossConfig;
  @Autowired
  private OSS oss;

  @Override
  public String upload(MultipartFile file, String bucketName) {
    try {
      oss.putObject(bucketName, file.getOriginalFilename(), file.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    log.info("文件 {} 存入OSS成功", file.getName());
    return null;
  }

  @Override
  public File download(String url) {
    return null;
  }

  private void createBucketIfNotExisting(String bucketName) {
    if (oss.doesBucketExist(bucketName)) {
      log.info("Bucket: {} 已存在", bucketName);
      return;
    }
    oss.createBucket(bucketName);
  }
}
