package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserInfoEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface UserInfoService extends IService<UserInfoEntity> {

  /**
   * 保存新用户
   *
   * @param user 用户信息
   * @return 返回码（成功则返回用户 id）
   */
  Integer addUser(UserInfoEntity user);

  /**
   * 更新用户信息
   *
   * @param user 用户信息
   * @return 返回码（成功则返回用户 id）
   */
  Integer updateUser(UserInfoEntity user);
}
