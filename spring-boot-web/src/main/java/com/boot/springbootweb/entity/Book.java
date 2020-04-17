package com.boot.springbootweb.entity;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Book.java 请求参数验证示例
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/31
 **/
@Data
public class Book {

  @NotNull(message = "id 不能为空", groups = ValidateGroup.Update.class)
  private Integer id;
  @NotBlank(message = "name 不能为空")
  @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间")
  private String name;
  @NotNull(message = "price 不能为空")
  @DecimalMin(value = "0.1", message = "价格不能低于 {value}")
  private BigDecimal price;
}