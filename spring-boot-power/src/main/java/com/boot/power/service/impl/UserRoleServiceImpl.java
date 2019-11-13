package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.RoleEntity;
import com.boot.power.entity.UserInfoEntity;
import com.boot.power.entity.UserRoleEntity;
import com.boot.power.mapper.UserRoleMapper;
import com.boot.power.service.RoleService;
import com.boot.power.service.UserInfoService;
import com.boot.power.service.UserRoleService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements
    UserRoleService {

  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private RoleService roleService;

  @Override
  public Integer addUserRoles(Integer userId, List<Integer> roleIds) {
    // 校验用户是否存在
    UserInfoEntity user = userInfoService.getById(userId);
    if (user == null) {
      return ReturnCode.NO_USER.getCode();
    }
    // 查询当前用户的所有角色
    List<UserRoleEntity> userRoleList = list(
        new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userId));
    List<UserRoleEntity> userRoles = new ArrayList<>(roleIds.size());
    LocalDateTime now = LocalDateTime.now();
    List<Integer> oldRoleIds = userRoleList.stream()
        .map(UserRoleEntity::getRoleId)
        .collect(Collectors.toList());
    for (Integer roleId : roleIds) {
      if (oldRoleIds.contains(roleId)) {
        continue;
      }
      UserRoleEntity role = new UserRoleEntity();
      role.setUserId(userId);
      role.setRoleId(roleId);
      role.setCreateDate(now);
      role.setUpdateDate(now);
      userRoles.add(role);
    }
    saveBatch(userRoles);
    return ReturnCode.SUCCESS.getCode();
  }

  @Override
  public Integer deleteUserRole(Integer userId, Integer roleId) {
    UserInfoEntity user = userInfoService.getById(userId);
    if (user == null) {
      return ReturnCode.NO_USER.getCode();
    }
    RoleEntity role = roleService.getById(roleId);
    if (role == null) {
      return ReturnCode.NO_ROLE.getCode();
    }
    remove(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userId)
        .eq(UserRoleEntity::getRoleId, roleId));
    return ReturnCode.SUCCESS.getCode();
  }

  @Override
  @Transactional
  public Integer updateUserRoles(Integer userId, List<Integer> roleIds) {
    // 判断用户是否存在
    UserInfoEntity user = userInfoService.getById(userId);
    if (user == null) {
      return ReturnCode.NO_USER.getCode();
    }
    // 查询当前用户的所有角色
    List<UserRoleEntity> userAllRoles = list(
        new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userId));
    List<Integer> userAllRoleIds = userAllRoles.stream()
        .map(UserRoleEntity::getRoleId)
        .collect(Collectors.toList());
    LocalDateTime now = LocalDateTime.now();
    Function<Integer, UserRoleEntity> function = roleId -> {
      UserRoleEntity entity = new UserRoleEntity();
      entity.setUserId(userId);
      entity.setRoleId(roleId);
      entity.setCreateDate(now);
      entity.setUpdateDate(now);
      return entity;
    };
    // 新增的角色
    List<UserRoleEntity> saveRoles = roleIds.stream()
        .filter(item -> !userAllRoleIds.contains(item))
        .map(function)
        .collect(Collectors.toList());
    // 删除的角色(主键 id)
    List<Integer> deleteIds = userAllRoles.stream()
        .filter(item -> !roleIds.contains(item.getRoleId()))
        .map(UserRoleEntity::getId)
        .collect(Collectors.toList());
    saveBatch(saveRoles);
    removeByIds(deleteIds);
    return ReturnCode.SUCCESS.getCode();
  }
}
