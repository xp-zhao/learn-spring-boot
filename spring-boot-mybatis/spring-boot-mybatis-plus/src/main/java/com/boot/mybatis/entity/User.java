package com.boot.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date
 **/
@Data
@TableName("user")
@Builder
public class User {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String name;
  private Integer age;
  private String email;
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime created;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updated;
}
