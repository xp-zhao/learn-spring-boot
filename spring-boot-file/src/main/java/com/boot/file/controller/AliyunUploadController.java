package com.boot.file.controller;

import com.boot.file.config.AliyunOSSConfig;
import com.boot.file.service.FileStore;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaoxiaoping
 * @Description: 阿里云OSS上传
 * @Date 2020-8-14
 **/
@RestController
@RequestMapping("/aliyun")
@Slf4j
public class AliyunUploadController {

  @Autowired
  private FileStore fileStore;
  @Autowired
  private AliyunOSSConfig ossConfig;
  
  @PostMapping("/upload")
  public String upload(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
      return "文件为空";
    }
    String originName = file.getOriginalFilename();
    // 文件后缀
    String suffix = originName.substring(originName.lastIndexOf("."));
    String fileName = UUID.randomUUID().toString().replace("-", "");
    fileStore.upload(file, ossConfig.getBucketName());
    log.info("上传成功");
    return "";
  }
}
