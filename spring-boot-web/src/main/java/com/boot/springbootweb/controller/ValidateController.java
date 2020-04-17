package com.boot.springbootweb.controller;

import com.boot.springbootweb.annotation.DateTime;
import com.boot.springbootweb.entity.Book;
import com.boot.springbootweb.entity.ValidateGroup;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ValidateController.java 参数验证示例
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@RestController
@RequestMapping("/validate")
@Validated
public class ValidateController {

  @GetMapping("/test")
  public String test(
      @NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) {
    return name;
  }

  @GetMapping("/book")
  public Book book(@Validated Book book) {
    return book;
  }

  @GetMapping("/date")
  public String date(@DateTime(message = "您输入的格式错误") String date) {
    return date;
  }

  @RequestMapping("/insert")
  public Book insert(@Validated Book book){
    return book;
  }

  @RequestMapping("/update")
  public Book update(@Validated(value = {ValidateGroup.Update.class}) Book book){
    return book;
  }
}