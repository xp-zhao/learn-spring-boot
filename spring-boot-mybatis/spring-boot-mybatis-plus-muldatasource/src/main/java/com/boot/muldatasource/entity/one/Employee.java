package com.boot.muldatasource.entity.one;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
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
@TableName("tbl_employee")
public class Employee implements Serializable {

  private static final long serialVersionUID = -8788743247890574219L;

  @TableId(type = IdType.AUTO)
  private Integer id;
  private String lastName;
  private String email;
  private Integer gender;
  private Integer age;
}