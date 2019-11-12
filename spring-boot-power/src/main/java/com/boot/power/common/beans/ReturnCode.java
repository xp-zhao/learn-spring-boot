package com.boot.power.common.beans;

import java.util.Arrays;

/**
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
public enum ReturnCode {
  /**
   * 操作成功
   */
  SUCCESS(1, "success"),
  /**
   * 用户名已存在
   */
  REPEAT_USER(-1, "用户名已存在"),
  /**
   * 用户不存在
   */
  NO_USER(-2, "用户不存在");
  private final Integer code;
  private final String msg;

  ReturnCode(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public static String getMsg(Integer code) {
    return Arrays.stream(ReturnCode.values())
        .filter(item -> item.getCode().equals(code))
        .map(ReturnCode::getMsg)
        .findFirst()
        .orElse("");
  }
}