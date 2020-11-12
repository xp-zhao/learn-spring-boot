package com.boot.file.service;

import com.boot.file.CropPoint;

/**
 * @author zhaoxiaoping
 * @Description: 图片处理服务接口
 * @Date 2020-11-11
 **/
public interface ImageHandlerService {

  /**
   * 获取图片信息
   *
   * @param objectName 图片名称
   * @return
   */
  String info(String objectName);

  /**
   * 图片旋转
   *
   * @param objectName 图片名称
   * @return
   */
  String rotate(String objectName);

  /**
   * 图片裁剪
   *
   * @param objectName 图片名称
   * @param point      坐标点
   * @return
   */
  String crop(String objectName, CropPoint point);
}
