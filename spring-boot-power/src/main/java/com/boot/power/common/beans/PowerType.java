package com.boot.power.common.beans;

import java.util.Arrays;

/**
 * 权限类型
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/14
 **/
public enum PowerType {
  /**
   * 菜单权限
   */
  MENU(1, "MENU"),
  /**
   * 功能权限
   */
  OPERATION(2, "OPERATION"),
  /**
   * 文件权限
   */
  FILE(3, "FILE"),
  /**
   * 页面元素权限
   */
  ELEMENT(4, "ELEMENT");
  private final Integer code;
  private final String type;

  PowerType(Integer code, String type) {
    this.code = code;
    this.type = type;
  }

  public Integer getCode() {
    return code;
  }

  public String getType() {
    return type;
  }

  public static String getType(Integer code) {
    return Arrays.stream(PowerType.values())
        .filter(item -> item.getCode().equals(code))
        .map(PowerType::getType)
        .findFirst()
        .orElse("");
  }
}