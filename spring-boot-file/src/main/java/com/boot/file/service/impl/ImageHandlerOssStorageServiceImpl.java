package com.boot.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.boot.file.CropPoint;
import com.boot.file.config.AliyunOSSConfig;
import com.boot.file.service.ImageHandlerService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiaoping
 * @Description: oss 存储
 * @Date 2020-11-11
 **/
@Service("ImageHandlerOssStorageService")
public class ImageHandlerOssStorageServiceImpl implements ImageHandlerService {

  private AliyunOSSConfig ossConfig;
  private OSS oss;

  @Autowired
  public ImageHandlerOssStorageServiceImpl(AliyunOSSConfig ossConfig, OSS oss) {
    this.ossConfig = ossConfig;
    this.oss = oss;
  }

  @Override
  public String info(String objectName) {
    OSSObject object = oss.getObject(ossConfig.getBucketName(), objectName);
    ObjectMetadata metadata = object.getObjectMetadata();
    return null;
  }

  @Override
  public String rotate(String objectName) {
    try {
      StringBuilder sbStyle = new StringBuilder();
      Formatter styleFormatter = new Formatter(sbStyle);
      String styleType = "image/rotate,90";
      String targetImage = objectName;
      styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
          BinaryUtil.toBase64String(targetImage.getBytes()),
          BinaryUtil.toBase64String(ossConfig.getBucketName().getBytes()));
      ProcessObjectRequest request = new ProcessObjectRequest(ossConfig.getBucketName(), objectName,
          sbStyle.toString());
      GenericResult processResult = oss.processObject(request);
      InputStream inputStream = processResult.getResponse().getContent();
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String crop(String objectName, CropPoint point) {
    try {
      StringBuilder sbStyle = new StringBuilder();
      Formatter styleFormatter = new Formatter(sbStyle);
      String styleType = String
          .format("image/crop,w_%s,h_%s,x_%s,y_%s,r_1", point.getWidth(), point.getHeight(),
              point.getX(), point.getY());
      String suffix = objectName.substring(objectName.lastIndexOf("."));
      String name = objectName.substring(0, objectName.lastIndexOf("."));
      String targetImage = name + "-cut" + suffix;
      styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
          BinaryUtil.toBase64String(targetImage.getBytes()),
          BinaryUtil.toBase64String(ossConfig.getBucketName().getBytes()));
      ProcessObjectRequest request = new ProcessObjectRequest(ossConfig.getBucketName(), objectName,
          sbStyle.toString());
      GenericResult processResult = oss.processObject(request);
      OSSObject targetObject = oss.getObject(ossConfig.getBucketName(), targetImage);
      InputStream targetInputStream = targetObject.getObjectContent();
      byte[] buffer = new byte[8 * 1024];
      int read;
      OutputStream outputStream = new FileOutputStream(new File(targetImage));
      while ((read = targetInputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, read);
      }
      InputStream inputStream = processResult.getResponse().getContent();
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
