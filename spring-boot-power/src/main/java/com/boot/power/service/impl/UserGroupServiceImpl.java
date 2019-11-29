package com.boot.power.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserGroupEntity;
import com.boot.power.mapper.UserGroupMapper;
import com.boot.power.service.UserGroupService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroupEntity> implements
    UserGroupService {

  @Override
  public ReturnCode addUserGroup(UserGroupEntity userGroup) {
    // 校验是否存在同名用户组
    String userGroupName = userGroup.getUserGroupName();
    UserGroupEntity entity = getOne(new LambdaQueryWrapper<UserGroupEntity>()
        .eq(UserGroupEntity::getUserGroupName, userGroupName));
    if (ObjectUtil.isNotNull(entity)) {
      return ReturnCode.REPEAT_USER_GROUP;
    }
    // 保存用户组信息
    LocalDateTime now = LocalDateTime.now();
    userGroup.setCreateDate(now);
    userGroup.setUpdateDate(now);
    save(userGroup);
    return ReturnCode.SUCCESS;
  }

  @Override
  public ReturnCode updateUserGroup(UserGroupEntity userGroup) {
    // 校验用户组是否存在
    UserGroupEntity entity = getById(userGroup.getId());
    if (ObjectUtil.isNull(entity)) {
      return ReturnCode.NO_USER_GROUP;
    }
    // 校验是否存在同名用户组
    UserGroupEntity repeatUserGroup = getOne(new LambdaQueryWrapper<UserGroupEntity>()
        .eq(UserGroupEntity::getId, userGroup.getId())
        .ne(UserGroupEntity::getUserGroupName, userGroup.getUserGroupName()));
    if (ObjectUtil.isNotNull(repeatUserGroup)) {
      return ReturnCode.REPEAT_USER_GROUP;
    }
    // 更新用户组信息
    userGroup.setUpdateDate(LocalDateTime.now());
    updateById(userGroup);
    return ReturnCode.SUCCESS;
  }
}
