package com.boot.springbootweb.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * CustomException.java 自定义异常
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 7322753810175426188L;
  @Getter
  @Setter
  private int code;

  public CustomException() {
    super();
  }

  public CustomException(int code, String message) {
    super(message);
    this.code = code;
  }
}