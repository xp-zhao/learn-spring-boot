package com.boot.springbootweb.validate;


import cn.hutool.core.util.ArrayUtil;
import com.boot.springbootweb.enums.BaseEnum;
import com.boot.springbootweb.validate.EnumValue.EnumValueValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import lombok.SneakyThrows;

/**
 * @author lenovo
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
    ElementType.FIELD,
    ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValueValidator.class})
public @interface EnumValue {

  String message() default "必须为指定值";

  String[] strValues() default {};

  int[] intValues() default {};

  Class<? extends BaseEnum>[] enumValues() default {};

  Class<? extends BaseEnum>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  //指定多个时使用
  @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
      ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {

    EnumValue[] value();
  }

  class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;
    private Class<? extends BaseEnum>[] enumValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
      strValues = constraintAnnotation.strValues();
      intValues = constraintAnnotation.intValues();
      enumValues = constraintAnnotation.enumValues();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
      if (ArrayUtil.isNotEmpty(strValues)) {
        if (value instanceof String) {
          for (String s : strValues) {
            if (s.equals(value)) {
              return true;
            }
          }
        }
      }
      if (ArrayUtil.isNotEmpty(intValues)) {
        if (value instanceof Integer) {
          for (int i : intValues) {
            if (value.equals(i)) {
              return true;
            }
          }
        }
      }
      if (ArrayUtil.isNotEmpty(enumValues)) {
        for (Class<? extends BaseEnum> enumValue : enumValues) {
          if (enumValue.isEnum()) {
            BaseEnum[] constants = enumValue.getEnumConstants();
            for (BaseEnum constant : constants) {
              if (constant.getValue().equals(value)) {
                return true;
              }
            }
          }
        }
      }
      return false;
    }
  }
}
