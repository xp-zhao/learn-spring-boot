package com.boot.springbootweb.config;

import com.boot.springbootweb.exception.CustomException;
import com.boot.springbootweb.exception.ErrorResponseEntity;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler.java 全局异常处理
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e,
      HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    CustomException exception = (CustomException) e;
    return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e,
      HttpServletResponse response) {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    RuntimeException exception = (RuntimeException) e;
    return new ErrorResponseEntity(400, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponseEntity exceptionHandler(Exception e) {
    return new ErrorResponseEntity(400, e.getMessage());
  }

  /**
   * 参数校验异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class,
      ConstraintViolationException.class})
  public ErrorResponseEntity validationErrorHandler(Exception ex) {
    String message = "";
    if (ex instanceof BindException) {
      message = ((BindException) ex).getBindingResult().getAllErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.joining(","));
    } else if (ex instanceof MethodArgumentNotValidException) {
      message = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.joining(","));
    } else if (ex instanceof ConstraintViolationException) {
      message = ((ConstraintViolationException) ex).getConstraintViolations()
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.joining(","));
    }
    return new ErrorResponseEntity(400, message);
  }
}