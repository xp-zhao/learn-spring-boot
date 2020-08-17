package com.boot.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.boot.file.config.AliyunOSSConfig;
import com.boot.file.service.FileStore;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
    String accessUrl = "";
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setObjectAcl(CannedAccessControlList.PublicRead);
      oss.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
      accessUrl = getAccessUrl(file.getOriginalFilename());
    } catch (IOException e) {
      e.printStackTrace();
    }
    log.info("文件 {} 存入OSS成功", file.getName());
    return accessUrl;
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

  private String getAccessUrl(String key) {
    GeneratePresignedUrlRequest request;
    request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), key);
    LocalDateTime now = LocalDateTime.now();
    // 设置 URL 过期时间(2 小时)
    now = now.plusHours(2);
    Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    request.setExpiration(date);
    URL url = oss.generatePresignedUrl(request);
    String file = url.getFile();
    return ossConfig.getAccessHost() + file;
  }
}
