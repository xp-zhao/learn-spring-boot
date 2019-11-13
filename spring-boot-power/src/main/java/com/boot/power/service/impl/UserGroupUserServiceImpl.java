package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserGroupEntity;
import com.boot.power.entity.UserGroupUserEntity;
import com.boot.power.entity.UserInfoEntity;
import com.boot.power.mapper.UsergroupUserMapper;
import com.boot.power.service.UserGroupService;
import com.boot.power.service.UserGroupUserService;
import com.boot.power.service.UserInfoService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组与用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserGroupUserServiceImpl extends
    ServiceImpl<UsergroupUserMapper, UserGroupUserEntity> implements
    UserGroupUserService {

  @Autowired
  private UserGroupService userGroupService;
  @Autowired
  private UserInfoService userInfoService;

  @Override
  public Integer addUserToUserGroup(Integer groupId, Integer userId) {
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (userGroupEntity == null) {
      return ReturnCode.NO_USER_GROUP.getCode();
    }
    UserInfoEntity userInfoEntity = userInfoService.getById(userId);
    if (userInfoEntity == null) {
      return ReturnCode.NO_USER.getCode();
    }
    UserGroupUserEntity userGroupUserEntity = getOne(new LambdaQueryWrapper<UserGroupUserEntity>()
        .eq(UserGroupUserEntity::getUsergroupId, groupId)
        .eq(UserGroupUserEntity::getUserId, userId));
    if (userGroupUserEntity != null) {
      return ReturnCode.REPEAT_USER_GROUP_USER.getCode();
    }
    UserGroupUserEntity entity = new UserGroupUserEntity();
    LocalDateTime now = LocalDateTime.now();
    entity.setUsergroupId(groupId);
    entity.setUserId(userId);
    entity.setCreateDate(now);
    entity.setUpdateDate(now);
    save(entity);
    return ReturnCode.SUCCESS.getCode();
  }

  @Override
  public Integer deleteUserFromUserGroup(Integer groupId, Integer userId) {
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (userGroupEntity == null) {
      return ReturnCode.NO_USER_GROUP.getCode();
    }
    UserInfoEntity userInfoEntity = userInfoService.getById(userId);
    if (userInfoEntity == null) {
      return ReturnCode.NO_USER.getCode();
    }
    remove(new LambdaQueryWrapper<UserGroupUserEntity>()
        .eq(UserGroupUserEntity::getUsergroupId, groupId)
        .eq(UserGroupUserEntity::getUserId, userId));
    return ReturnCode.SUCCESS.getCode();
  }
}
