package com.boot.springbootweb.config;

import com.boot.springbootweb.exception.CustomException;
import com.boot.springbootweb.exception.ErrorResponseEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler.java 全局异常处理
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e,
      HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    CustomException exception = (CustomException) e;
    return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e,
      HttpServletResponse response){
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    RuntimeException exception = (RuntimeException) e;
    return new ErrorResponseEntity(400, exception.getMessage());
  }
}