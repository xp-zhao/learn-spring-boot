package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.dto.UserGroupUserDTO;
import com.boot.power.entity.UserGroupUserEntity;
import java.util.List;

/**
 * <p>
 * 用户组与用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface UserGroupUserService extends IService<UserGroupUserEntity> {

  /**
   * 将用户添加到用户组
   *
   * @param groupId 用户组 id
   * @param userId 用户 id
   * @return 返回码
   */
  ReturnCode addUserToUserGroup(Integer groupId, Integer userId);

  /**
   * 从用户组删除用户
   *
   * @param groupId 用户组 id
   * @param userId 用户 id
   * @return 返回码
   */
  ReturnCode deleteUserFromUserGroup(Integer groupId, Integer userId);

  /**
   * 查询指定用户组下的所有用户
   *
   * @param groupId 用户组 id
   * @return 用户列表
   */
  List<UserGroupUserDTO> queryAllUserByGroup(Integer groupId);
}
