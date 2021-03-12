package com.boot.springbootweb.enums;

import cn.hutool.core.convert.Convert;

/**
 * @author lenovo
 */

public enum StatusEnum implements BaseEnum {
  /**
   * 处理中
   */
  PROCESSING(1, "处理中"),
  /**
   * 已完成
   */
  SUCCESS(2, "订单已完成");
  Integer value;
  String desc;

  StatusEnum(Integer value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  @Override
  public String getValue() {
    return Convert.toStr(value);
  }

  public String getDesc() {
    return desc;
  }
}
