package com.boot.springbootweb;

import com.boot.springbootweb.enums.StatusEnum;
import org.junit.Test;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-7-21
 **/
public class BeanInfoTest {

  @Test
  public void testBeanInfo() {
    StatusEnum[] enums = StatusEnum.values();
    for (StatusEnum item : enums) {
      System.out.println(item.getValue());
    }
  }
}
