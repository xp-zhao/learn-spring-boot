package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.entity.UserRoleEntity;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface UserRoleService extends IService<UserRoleEntity> {

  /**
   * 给用户分配角色
   *
   * @param userId 用户 id
   * @param roleIds 角色 id 列表
   * @return 返回码
   */
  Integer addUserRoles(Integer userId, List<Integer> roleIds);

  /**
   * 删除用户的指定角色
   *
   * @param userId 用户 id
   * @param roleId 角色 id
   * @return 返回码
   */
  Integer deleteUserRole(Integer userId, Integer roleId);

  /**
   * 修改用户角色
   *
   * @param userId 用户 id
   * @param roleIds 角色 id 列表
   * @return 返回码
   */
  Integer updateUserRoles(Integer userId, List<Integer> roleIds);
}
