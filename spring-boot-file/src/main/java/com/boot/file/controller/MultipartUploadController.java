package com.boot.file.controller;

import cn.hutool.core.io.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaoxiaoping
 * @Description: 分片上传控制层
 * @Date 2020-7-27
 **/
@RestController
@RequestMapping("/slice")
@Slf4j
public class MultipartUploadController {

  private static final String BASE_PATH = "D:\\data\\book";

  public static void main(String[] args) throws IOException {
    String path = BASE_PATH + File.separator + UUID.randomUUID().toString();
    Files.createDirectory(Paths.get(path));
  }

  /**
   * 获取文件 id
   */
  @GetMapping("/getFileId")
  public String getFileId(@RequestParam("fileName") String fileName) {
    log.info("上传文件名 {}", fileName);
    String filePath = BASE_PATH + File.separator + fileName.substring(0, fileName.lastIndexOf("."));
    Path path = Paths.get(filePath);
    try {
      Files.createDirectory(path);
    } catch (IOException e) {
      log.error("文件夹 {} 创建失败", fileName, e);
    }
    return UUID.randomUUID().toString();
  }

  /**
   * 单片上传
   */
  @PostMapping("/upload")
  public String upload(String fileId, Integer no, MultipartFile file) {
    log.info("上传文件第 {} 片", no);
    String path = BASE_PATH + File.separator + fileId;
    String fileSuffix = ".tmp";
    File saveFile = new File(path + File.separator + no + fileSuffix);
    FileUtil.mkParentDirs(saveFile);
    try {
      file.transferTo(saveFile);
    } catch (IOException e) {
      log.error("文件 {} 分片 {} 上传失败", fileId, no, e);
    }
    return "success";
  }

  /**
   * 文件合并
   */
  @PostMapping("/mergeFile")
  public String mergeFile(String fileId) {
    String path = BASE_PATH + File.separator + "image.jpg";
    try (FileChannel outChannel = new FileOutputStream(path).getChannel()) {
      List<String> fileNames = FileUtil.listFileNames(BASE_PATH + File.separator + fileId);
      FileChannel sliceChannel;
      String folderPath = BASE_PATH + File.separator + fileId;
      fileNames.sort(Comparator.comparing(s ->
          Integer.valueOf(s.substring(0, s.lastIndexOf(".")))
      ));
      for (String fileName : fileNames) {
        String filePath = folderPath + File.separator + fileName;
        sliceChannel = new FileInputStream(filePath).getChannel();
        sliceChannel.transferTo(0, sliceChannel.size(), outChannel);
        sliceChannel.close();
        FileUtil.del(filePath);
      }
      FileUtil.del(folderPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "success";
  }
}
