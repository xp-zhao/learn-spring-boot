package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserInfoEntity;
import com.boot.power.mapper.UserinfoMapper;
import com.boot.power.service.UserInfoService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserinfoMapper, UserInfoEntity> implements
    UserInfoService {

  @Override
  public Integer addUser(UserInfoEntity user) {
    // 查询用户名是否已存在
    UserInfoEntity entity = getOne(new LambdaQueryWrapper<UserInfoEntity>()
        .eq(UserInfoEntity::getUserName, user.getUserName()));
    if (entity != null) {
      return ReturnCode.REPEAT_USER.getCode();
    }
    LocalDateTime now = LocalDateTime.now();
    user.setCreateDate(now);
    user.setUpdateDate(now);
    save(user);
    return user.getId();
  }

  @Override
  public Integer updateUser(UserInfoEntity user) {
    // 判断用户是否存在
    UserInfoEntity entity = getById(user.getId());
    if (entity == null) {
      return ReturnCode.NO_USER.getCode();
    }
    // 查询用户名是否存在
    UserInfoEntity newEntity = getOne(new LambdaQueryWrapper<UserInfoEntity>()
        .eq(UserInfoEntity::getUserName, user.getUserName())
        .ne(UserInfoEntity::getId, user.getId()));
    if (newEntity != null) {
      return ReturnCode.REPEAT_USER.getCode();
    }
    // 更新用户信息
    user.setUpdateDate(LocalDateTime.now());
    updateById(user);
    return user.getId();
  }
}
