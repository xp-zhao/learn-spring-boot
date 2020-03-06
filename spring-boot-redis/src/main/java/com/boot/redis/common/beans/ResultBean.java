package com.boot.redis.common.beans;

import java.io.Serializable;
import lombok.Data;

/**
 * ResultBean.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
@Data
public class ResultBean<T> implements Serializable {

  private static final long serialVersionUID = -3117738626765913582L;
  public static final int SUCCESS = 0;
  public static final int UNKNOWN_EXCEPTION = -99;
  /**
   * 返回码：0 成功
   */
  private int code = SUCCESS;
  /**
   * 返回的信息（主要是出错的信息）
   */
  private String msg = "success";

  /**
   * 返回的数据
   */
  private T data;

  public ResultBean() {
    super();
  }

  public ResultBean(T data) {
    super();
    this.data = data;
  }

  public ResultBean(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ResultBean(Throwable t) {
    super();
    this.msg = t.toString();
    this.code = UNKNOWN_EXCEPTION;
  }
}