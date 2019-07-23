package com.boot.log.annotaion;

import com.boot.log.common.LogAnnotationEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解，用于日志输出的标签,只有加在受Spring管理类的方法上才有效果
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

  LogAnnotationEnum type() default LogAnnotationEnum.DEBUG;
}
