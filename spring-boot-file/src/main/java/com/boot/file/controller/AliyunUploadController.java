package com.boot.file.controller;

import cn.hutool.core.util.StrUtil;
import com.boot.file.service.FileStore;
import com.boot.file.service.ImageHandlerService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
  @Qualifier("ImageHandlerLocalStorageService")
  @Autowired
  private ImageHandlerService imageHandlerService;

  @PostMapping("/upload")
  public String upload(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
      return "文件为空";
    }
    String url = fileStore.upload(file);
    log.info("上传成功, 访问链接 {}", url);
    return url;
  }

  @PostMapping("/rotate")
  public String rotate(String objectName) throws IOException {
    if (StrUtil.isBlank(objectName)) {
      return "文件为空";
    }
    String url = imageHandlerService.rotate(objectName);
    log.info("旋转成功, 访问链接 {}", url);
    return url;
  }

}
