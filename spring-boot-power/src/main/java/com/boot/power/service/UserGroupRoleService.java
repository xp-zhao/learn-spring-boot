package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserGroupRoleEntity;

/**
 * <p>
 * 用户组与角色表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface UserGroupRoleService extends IService<UserGroupRoleEntity> {

  /**
   * 为用户组设置角色
   *
   * @param groupId 用户组 id
   * @param roleId 角色 id
   * @return 返回码
   */
  ReturnCode addRoleToUserGroup(Integer groupId, Integer roleId);

  /**
   * 删除用户组的角色
   *
   * @param groupId 用户组 id
   * @param roleId 角色 id
   * @return 返回码
   */
  ReturnCode deleteRoleFromUserGroup(Integer groupId, Integer roleId);

}
