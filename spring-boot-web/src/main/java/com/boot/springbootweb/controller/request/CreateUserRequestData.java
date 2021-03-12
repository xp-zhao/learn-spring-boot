package com.boot.springbootweb.controller.request;

import com.boot.springbootweb.enums.StatusEnum;
import com.boot.springbootweb.validate.EnumValue;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description: 创建用户请求参数
 * @Date 2021-3-12
 **/
@Data
public class CreateUserRequestData {

  @NotNull(message = "name 不能为空")
  private String name;
  private String type;
  @EnumValue(enumValues = StatusEnum.class, message = "状态值不在范围内")
  private String status;
}
