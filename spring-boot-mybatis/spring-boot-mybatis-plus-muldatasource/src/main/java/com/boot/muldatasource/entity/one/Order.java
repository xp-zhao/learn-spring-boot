package com.boot.muldatasource.entity.one;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Employee.java 数据库表映射实体
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tbl_order")
public class Order implements Serializable {

  private static final long serialVersionUID = -8788743247890574219L;

  @TableId
  private String orderId;
  private String lastName;
  private String email;
  @TableField(updateStrategy = FieldStrategy.IGNORED)
  private Integer gender;
  private Integer age;
}