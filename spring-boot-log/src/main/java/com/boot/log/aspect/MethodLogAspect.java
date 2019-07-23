package com.boot.log.aspect;

import com.boot.log.annotaion.LogAnnotation;
import com.boot.log.common.LogAnnotationEnum;
import com.boot.log.common.MyLogFactory;
import com.boot.log.common.MyLogger;
import com.boot.log.format.SimpleLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * MethodLogAspect.java 方法日志切面
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/23
 **/
@Aspect
@Component
public class MethodLogAspect {

  private static final MyLogger logger = MyLogFactory.getLogger(MethodLogAspect.class);

  @Around (value = "@annotation(logAnnotation)")
  public Object servicePointcut(ProceedingJoinPoint point, LogAnnotation logAnnotation)
      throws Throwable {
    long startTime = System.currentTimeMillis();

    try {
      // 执行方法
      Object result = point.proceed();
      //耗时
      long du = System.currentTimeMillis() - startTime;
      showlog(point, logAnnotation, result, du);
      return result;
    } catch (Exception e) {
      long du = System.currentTimeMillis() - startTime;
      showlog(point, logAnnotation, "方法执行异常", du);
      throw e;
    }
  }


  private void showlog(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotion, Object result,
      long du) throws NoSuchMethodException, SecurityException {
    String className = joinPoint.getSignature().getDeclaringTypeName();
    SimpleLog logvo = new SimpleLog(className.substring(className.lastIndexOf(".") + 1));
    logvo.setMethodName(joinPoint.getSignature().getName());
    logvo.setResult(result);
    logvo.setArgs(joinPoint.getArgs());
    logvo.setDu(du);
    if (LogAnnotationEnum.DEBUG == logAnnotion.type()) {
      logger.debug(logvo);
    } else {
      logger.info(logvo);
    }
  }
}