package com.boot.muldatasource.entity.two;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2019-12-19
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("Student")
public class StudentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableField("SId")
  private String SId;

  @TableField("Sname")
  private String Sname;

  @TableField("Sage")
  private LocalDateTime Sage;

  @TableField("Ssex")
  private String Ssex;
}
