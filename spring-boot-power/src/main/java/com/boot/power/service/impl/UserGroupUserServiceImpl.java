package com.boot.power.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.dto.UserGroupUserDTO;
import com.boot.power.entity.UserGroupEntity;
import com.boot.power.entity.UserGroupUserEntity;
import com.boot.power.entity.UserInfoEntity;
import com.boot.power.mapper.UserGroupUserMapper;
import com.boot.power.service.UserGroupService;
import com.boot.power.service.UserGroupUserService;
import com.boot.power.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    ServiceImpl<UserGroupUserMapper, UserGroupUserEntity> implements
    UserGroupUserService {

  @Autowired
  private UserGroupService userGroupService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserGroupUserMapper userGroupUserMapper;

  @Override
  public ReturnCode addUserToUserGroup(Integer groupId, Integer userId) {
    // 判断用户组是否存在
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (ObjectUtil.isNull(userGroupEntity)) {
      return ReturnCode.NO_USER_GROUP;
    }
    // 判断用户是否存在
    UserInfoEntity userInfoEntity = userInfoService.getById(userId);
    if (ObjectUtil.isNull(userInfoEntity)) {
      return ReturnCode.NO_USER;
    }
    // 判断用户是否已经在当前用户组了
    UserGroupUserEntity userGroupUserEntity = getOne(new LambdaQueryWrapper<UserGroupUserEntity>()
        .eq(UserGroupUserEntity::getUserGroupId, groupId)
        .eq(UserGroupUserEntity::getUserId, userId));
    if (ObjectUtil.isNotNull(userGroupUserEntity)) {
      return ReturnCode.REPEAT_USER_GROUP_USER;
    }
    // 将用户添加到用户组中
    UserGroupUserEntity entity = new UserGroupUserEntity();
    LocalDateTime now = LocalDateTime.now();
    entity.setUserGroupId(groupId);
    entity.setUserId(userId);
    entity.setCreateDate(now);
    entity.setUpdateDate(now);
    save(entity);
    return ReturnCode.SUCCESS;
  }

  @Override
  public ReturnCode deleteUserFromUserGroup(Integer groupId, Integer userId) {
    // 判断用户组是否存在
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (ObjectUtil.isNull(userGroupEntity)) {
      return ReturnCode.NO_USER_GROUP;
    }
    // 判断用户是否存在于当前用户组中
    UserGroupUserEntity userGroupUserEntity = getOne(
        new LambdaQueryWrapper<UserGroupUserEntity>()
            .eq(UserGroupUserEntity::getUserGroupId, groupId)
            .eq(UserGroupUserEntity::getUserId, userId));
    if (ObjectUtil.isNull(userGroupUserEntity)) {
      return ReturnCode.NO_USER;
    }
    remove(new LambdaQueryWrapper<UserGroupUserEntity>()
        .eq(UserGroupUserEntity::getUserGroupId, groupId)
        .eq(UserGroupUserEntity::getUserId, userId));
    return ReturnCode.SUCCESS;
  }

  @Override
  public List<UserGroupUserDTO> queryAllUserByGroup(Integer groupId, Integer pageNum, Integer pageSize) {
    if(ObjectUtil.isNull(pageNum) || ObjectUtil.isNull(pageSize)){
      return userGroupUserMapper.queryAllUserByGroup(groupId);
    }
    PageHelper.startPage(pageNum, pageSize);
    List<UserGroupUserDTO> result = userGroupUserMapper.queryAllUserByGroup(groupId);
    PageInfo<UserGroupUserDTO> page = new PageInfo<>(result);
    return page.getList();
  }
}
