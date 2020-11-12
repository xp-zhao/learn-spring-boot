package com.boot.file;

import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description: 图片裁剪坐标点
 * @Date 2020-11-11
 **/
@Data
public class CropPoint {

  private Integer width;
  private Integer height;
  private Integer x;
  private Integer y;
}
