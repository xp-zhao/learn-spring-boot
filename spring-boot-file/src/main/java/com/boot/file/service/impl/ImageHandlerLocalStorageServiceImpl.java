package com.boot.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.boot.file.CropPoint;
import com.boot.file.config.AliyunOSSConfig;
import com.boot.file.service.ImageHandlerService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiaoping
 * @Description: 本地存储
 * @Date 2020-11-11
 **/
@Service("ImageHandlerLocalStorageService")
public class ImageHandlerLocalStorageServiceImpl implements ImageHandlerService {

  private AliyunOSSConfig ossConfig;
  private OSS oss;

  @Autowired
  public ImageHandlerLocalStorageServiceImpl(AliyunOSSConfig ossConfig, OSS oss) {
    this.ossConfig = ossConfig;
    this.oss = oss;
  }

  @Override
  public String info(String objectName){
    String style = "image/info";
    GetObjectRequest request = new GetObjectRequest(ossConfig.getBucketName(), objectName);
    request.setProcess(style);
    OSSObject object1 = oss.getObject(request);
    try {
      InputStream objectContent = object1.getObjectContent();
      byte[] bytes = new byte[objectContent.available()];
      objectContent.read(bytes);
      String str = new String(bytes);
      System.out.println(str);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String rotate(String objectName) {
//    String style = "image/rotate,1";
//    GetObjectRequest request = new GetObjectRequest(ossConfig.getBucketName(), objectName);
//    request.setProcess(style);
//    File targetFile = new File(objectName);
//    oss.getObject(request, targetFile);
//    oss.putObject(ossConfig.getBucketName(), objectName, targetFile);
    try {
      OSSObject object = oss.getObject(ossConfig.getBucketName(), objectName);
      InputStream is = object.getObjectContent();
      File targetFile = new File(objectName);
      Thumbnails.of(is).scale(1f).rotate(0.3).toFile(targetFile);
      oss.putObject(ossConfig.getBucketName(), objectName, targetFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return getAccessUrl(objectName);
  }

  @Override
  public String crop(String objectName, CropPoint point) {
    String style = String
        .format("image/crop,w_%s,h_%s,x_%s,y_%s,r_1", point.getWidth(), point.getHeight(),
            point.getX(), point.getY());
    GetObjectRequest request = new GetObjectRequest(ossConfig.getBucketName(), objectName);
    request.setProcess(style);
    String suffix = objectName.substring(objectName.lastIndexOf("."));
    String name = objectName.substring(0, objectName.lastIndexOf("."));
    String targetFileName = name + "-cut" + suffix;
    File targetFile = new File(targetFileName);
    oss.getObject(request, targetFile);
    oss.putObject(ossConfig.getBucketName(), objectName, targetFile);
    return null;
  }

  private String getAccessUrl(String key) {
    GeneratePresignedUrlRequest request;
    request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), key);
    LocalDateTime now = LocalDateTime.now();
    // 设置 URL 过期时间(2 小时)
    now = now.plusHours(24);
    Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    request.setExpiration(date);
    URL url = oss.generatePresignedUrl(request);
    String file = url.getFile();
    return ossConfig.getAccessHost() + file;
  }
}
