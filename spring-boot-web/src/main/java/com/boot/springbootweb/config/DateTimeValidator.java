package com.boot.springbootweb.config;

import com.boot.springbootweb.annotation.DateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * DateTimeValidator.java 日期格式验证
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

  private DateTime dateTime;

  @Override
  public void initialize(DateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    // 如果 value 为空，则不进行格式验证，为空验证有 @NotBlank @NotNull @NotEmpty 等注解来进行控制
    if (value == null) {
      return true;
    }
    String format = dateTime.format();
    if (value.length() != format.length()) {
      return false;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.replace("y", "u"))
        .withResolverStyle(ResolverStyle.STRICT);
    try {
      formatter.parse(value);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}