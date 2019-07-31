package com.boot.springbootweb.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.boot.springbootweb.config.DateTimeValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义注解，验证时间格式
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTime {

  String message() default "格式错误";

  String format() default "yyyy-MM-dd";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
