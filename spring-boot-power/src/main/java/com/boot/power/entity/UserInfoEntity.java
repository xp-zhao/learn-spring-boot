package com.boot.power.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.power.common.validater.ValidateGroup;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@TableName("tbl_user_info")
public class UserInfoEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户 id
   */
  @TableId(value = "id", type = IdType.AUTO)
  @NotNull(message = "用户 id 不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Query.class})
  private Integer id;

  /**
   * 用户名称
   */
  @NotBlank(message = "用户名不能为空", groups = {ValidateGroup.Add.class, ValidateGroup.Update.class})
  private String userName;

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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
    return "UserInfoEntity{" +
        "id=" + id +
        ", userName=" + userName +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
  }
}
