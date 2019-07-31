package com.boot.springbootweb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorResponseEntity.java 异常信息格式
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseEntity {

  private int code;
  private String message;
}