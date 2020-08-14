package com.boot.file.service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaoxiaoping
 * @Description: 文件存储接口
 * @Date 2020-8-14
 **/
public interface FileStore {

  /**
   * 上传文件
   *
   * @param file
   * @param bucketName
   * @return
   */
  String upload(MultipartFile file, String bucketName);

  /**
   * 下载文件
   *
   * @param url
   * @return
   */
  File download(String url);
}
