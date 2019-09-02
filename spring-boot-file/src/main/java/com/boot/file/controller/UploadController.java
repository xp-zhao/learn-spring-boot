package com.boot.file.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadController.java 文件上传控制层
 *
 * @author: zhaoxiaoping
 * @date: 2019/09/02
 **/
@RestController
public class UploadController {

  @PostMapping("/upload")
  public String upload(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      return "文件为空";
    }
    return file.getOriginalFilename();
  }
}