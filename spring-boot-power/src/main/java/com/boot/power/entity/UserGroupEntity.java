package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.power.common.validater.ValidateGroup;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_usergroup")
public class UserGroupEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户组 id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 用户组名称
   */
  @NotBlank(message = "用户组名称不能为空", groups = {ValidateGroup.Add.class, ValidateGroup.Update.class})
  private String userGroupName;

  /**
   * 创建时间
   */
  private LocalDateTime createDate;

  /**
   * 更新时间
   */
  private LocalDateTime updateDate;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserGroupName() {
    return userGroupName;
  }

  public void setUserGroupName(String userGroupName) {
    this.userGroupName = userGroupName;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public String toString() {
    return "UserGroupEntity{" +
        "id=" + id +
        ", userGroupName=" + userGroupName +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
  }
}
