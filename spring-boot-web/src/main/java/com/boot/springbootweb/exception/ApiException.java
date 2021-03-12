package com.boot.springbootweb.exception;

import lombok.Getter;

/**
 * @author zhaoxiaoping
 * @Description: 接口请求参数校验异常
 * @Date 2021-3-12
 **/
public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -4885646078016035224L;

  @Getter
  private String msg;

  public ApiException(String msg) {
    this.msg = msg;
  }
}
