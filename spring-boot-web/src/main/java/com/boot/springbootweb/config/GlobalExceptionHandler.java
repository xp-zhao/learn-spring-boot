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
  public ErrorResponseEntity exceptionHandler(Exception e){
    return new ErrorResponseEntity(400, e.getMessage());
  }

  /**
   * 实体对象参数校验失败后抛出的异常
   */
  @ExceptionHandler(BindException.class)
  public ErrorResponseEntity bindExceptionHandler(BindException e) {
    String message = e.getBindingResult().getAllErrors().stream().map(
        DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
    return new ErrorResponseEntity(400, message);
  }

  /**
   * 单个参数校验失败抛出的异常
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ErrorResponseEntity constraintExceptionHandler(ConstraintViolationException e) {
    String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(","));
    return new ErrorResponseEntity(400, message);
  }
//  @Override
//  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
//      HttpHeaders headers, HttpStatus status, WebRequest request){
//    if (ex instanceof MethodArgumentNotValidException) {
//      MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
//      return new ResponseEntity<>(new ErrorResponseEntity(status.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
//    }
//    if (ex instanceof MethodArgumentTypeMismatchException) {
//      MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
//      logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
//          + ",信息：" + exception.getLocalizedMessage());
//      return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
//    }
//    return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
//  }
}