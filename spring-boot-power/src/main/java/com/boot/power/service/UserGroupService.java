package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.entity.UserGroupEntity;

/**
 * <p>
 * 用户组表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface UserGroupService extends IService<UserGroupEntity> {

  /**
   * 添加新的用户组
   *
   * @param userGroup 用户组实体
   * @return 返回码
   */
  Integer addUserGroup(UserGroupEntity userGroup);

  /**
   * 更新用户组信息
   *
   * @param userGroup 用户组实体
   * @return 返回码
   */
  Integer updateUserGroup(UserGroupEntity userGroup);
}
