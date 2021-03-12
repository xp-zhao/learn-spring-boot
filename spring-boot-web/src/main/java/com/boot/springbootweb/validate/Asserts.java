package com.boot.springbootweb.validate;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.boot.springbootweb.exception.ApiException;
import java.util.Optional;

/**
 * @author zhaoxiaoping
 * @Description: 断言工具类
 * @Date 2021-3-12
 **/
public class Asserts {

  public static void assertTrue(String description, boolean value) {
    if (!value) {
      throw new ApiException(description);
    }
  }

  public static void assertFieldNotEmpty(String field, String value) {
    assertFieldNotEmpty(field, value, false);
  }

  public static void assertFieldNotEmpty(String field, String value, boolean trim) {
    value = Optional.ofNullable(value).orElse(StrUtil.EMPTY);
    if (trim) {
      value = value.trim();
    }
    if (StrUtil.isBlank(value)) {
      throw new ApiException(field + "不应为空");
    }
  }

  /**
   * 校验字段不为 null
   *
   * @param field 字段名
   * @param value 字段值
   */
  public static void assertFieldNotNull(String field, Object value) {
    if (ObjectUtil.isNull(value)) {
      throw new ApiException(field + "不应为 Null");
    }
  }
}
