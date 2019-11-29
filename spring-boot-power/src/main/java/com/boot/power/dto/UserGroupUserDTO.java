package com.boot.power.dto;

import com.boot.power.entity.UserGroupUserEntity;
import lombok.Data;
import lombok.ToString;

/**
 * UserGroupUserDTO.java 用户组用户传输对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/29
 **/
@Data
@ToString
public class UserGroupUserDTO extends UserGroupUserEntity {

  private static final long serialVersionUID = -8523664315353087366L;
  /**
   * 用户组名称
   */
  private String userGroupName;
  /**
   * 用户名称
   */
  private String userName;
}