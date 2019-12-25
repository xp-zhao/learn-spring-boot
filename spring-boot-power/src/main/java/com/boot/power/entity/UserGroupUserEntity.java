package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 用户组与用户表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_user_group_user")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupUserEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 用户组 id
   */
  private Integer userGroupId;

  /**
   * 用户 id
   */
  private Integer userId;

  /**
   * 创建时间
   */
  private LocalDateTime createDate;

  /**
   * 更新时间
   */
  private LocalDateTime updateDate;
}
