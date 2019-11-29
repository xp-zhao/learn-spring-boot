package com.boot.power.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserGroupRoleEntity;
import com.boot.power.service.UserGroupRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import net.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户组与角色表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "用户组与角色关联接口")
@RestController
public class UserGroupRoleController {

  @Autowired
  private UserGroupRoleService userGroupRoleService;

  @ApiOperation("查询单个用户组的所有角色")
  @ApiImplicitParam(name = "id", value = "用户组 id", required = true)
  @GetMapping("/groups/{id}/roles")
  public ResultBean queryAllRoleByUserGroup(@PathVariable Integer id) {
    List<Integer> roleIds = userGroupRoleService.list(new LambdaQueryWrapper<UserGroupRoleEntity>()
        .eq(UserGroupRoleEntity::getUserGroupId, id))
        .stream()
        .map(UserGroupRoleEntity::getRoleId)
        .collect(Collectors.toList());
    return new ResultBean(roleIds);
  }

  @ApiOperation("为用户组设置角色")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true),
      @ApiImplicitParam(name = "roleId", value = "角色 id", required = true)
  })
  @PostMapping("/groups/{groupId}/roles/{roleId}")
  public ResultBean addRoleToUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer roleId) {
    ReturnCode returnCode = userGroupRoleService.addRoleToUserGroup(groupId, roleId);
    ResultBean result = new ResultBean();
    result.setCode(returnCode.getCode());
    result.setMsg(returnCode.getMsg());
    return result;
  }

  @ApiOperation("删除用户组的角色")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true),
      @ApiImplicitParam(name = "roleId", value = "角色 id", required = true)
  })
  @DeleteMapping("/groups/{groupId}/roles/{roleId}")
  public ResultBean deleteRoleFromUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer roleId) {
    ReturnCode returnCode = userGroupRoleService.deleteRoleFromUserGroup(groupId, roleId);
    ResultBean result = new ResultBean();
    result.setCode(returnCode.getCode());
    result.setMsg(returnCode.getMsg());
    return result;
  }
}

