package com.boot.file.oss;

import com.boot.file.CropPoint;
import com.boot.file.service.ImageHandlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 图像处理测试
 * @Date 2020-11-11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageHandlerTest {

  @Qualifier("ImageHandlerLocalStorageService")
  @Autowired
  private ImageHandlerService imageHandlerService;

  @Test
  public void testInfo(){
    String info = imageHandlerService.info("sssss.png");
  }
  
  @Test
  public void testCrop() {
    CropPoint point = new CropPoint();
    point.setWidth(150);
    point.setHeight(150);
    point.setX(0);
    point.setY(0);
    imageHandlerService.crop("sssss.png", point);
  }
}
