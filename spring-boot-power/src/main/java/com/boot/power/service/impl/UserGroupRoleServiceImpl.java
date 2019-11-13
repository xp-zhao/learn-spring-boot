package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.RoleEntity;
import com.boot.power.entity.UserGroupEntity;
import com.boot.power.entity.UserGroupRoleEntity;
import com.boot.power.entity.UserGroupUserEntity;
import com.boot.power.mapper.UsergroupRoleMapper;
import com.boot.power.service.RoleService;
import com.boot.power.service.UserGroupRoleService;
import com.boot.power.service.UserGroupService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组与角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserGroupRoleServiceImpl extends
    ServiceImpl<UsergroupRoleMapper, UserGroupRoleEntity> implements
    UserGroupRoleService {

  @Autowired
  private UserGroupService userGroupService;
  @Autowired
  private RoleService roleService;

  @Override
  public Integer addRoleToUserGroup(Integer groupId, Integer roleId) {
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (userGroupEntity == null) {
      return ReturnCode.NO_USER_GROUP.getCode();
    }
    RoleEntity roleEntity = roleService.getById(roleId);
    if (roleEntity == null) {
      return ReturnCode.NO_ROLE.getCode();
    }
    // 当前用户组的所有角色
    List<Integer> roleIds = list(new LambdaQueryWrapper<UserGroupRoleEntity>()
        .eq(UserGroupRoleEntity::getUsergroupId, groupId))
        .stream()
        .map(UserGroupRoleEntity::getRoleId)
        .collect(Collectors.toList());
    if (roleIds.contains(roleId)) {
      return ReturnCode.REPEAT_USER_GROUP_ROLE.getCode();
    }
    LocalDateTime now = LocalDateTime.now();
    UserGroupRoleEntity entity = new UserGroupRoleEntity();
    entity.setUsergroupId(groupId);
    entity.setRoleId(roleId);
    entity.setCreateDate(now);
    entity.setUpdateDate(now);
    save(entity);
    return ReturnCode.SUCCESS.getCode();
  }

  @Override
  public Integer deleteRoleFromUserGroup(Integer groupId, Integer roleId) {
    UserGroupEntity userGroupEntity = userGroupService.getById(groupId);
    if (userGroupEntity == null) {
      return ReturnCode.NO_USER_GROUP.getCode();
    }
    RoleEntity roleEntity = roleService.getById(roleId);
    if (roleEntity == null) {
      return ReturnCode.NO_ROLE.getCode();
    }
    remove(new LambdaQueryWrapper<UserGroupRoleEntity>()
        .eq(UserGroupRoleEntity::getUsergroupId, groupId)
        .eq(UserGroupRoleEntity::getRoleId, roleEntity));
    return ReturnCode.SUCCESS.getCode();
  }
}
