package com.boot.power.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserRoleEntity;
import com.boot.power.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色关联表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "用户角色管理相关接口")
@RestController
public class UserRoleController {

  @Autowired
  private UserRoleService userRoleService;


  @ApiOperation("获取指定角色下的用户列表")
  @ApiImplicitParam(name = "roleId", value = "角色 id", required = true, dataType = "Long")
  @GetMapping("/roles/{roleId}/users")
  public ResultBean getUserListByRoleId(@PathVariable Integer roleId) {
    List<UserRoleEntity> userList = userRoleService
        .list(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getRoleId, roleId));
    return new ResultBean(userList);
  }

  @ApiOperation("给用户分配角色")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true, dataType = "Long")
  })
  @PostMapping("/users/{userId}/roles")
  public ResultBean addUserRoles(
      @PathVariable Integer userId,
      @RequestParam("roleIds") @ApiParam(value = "角色 Id 列表") List<Integer> roleIds) {
    Integer code = userRoleService.addUserRoles(userId, roleIds);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }

  @ApiOperation("删除指定用户的指定角色")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true, dataType = "Long"),
      @ApiImplicitParam(name = "roleId", value = "角色 id", required = true, dataType = "Long")
  })
  @DeleteMapping("/users/{userId}/roles/{roleId}")
  public ResultBean deleteUserRole(@PathVariable Integer userId, @PathVariable Integer roleId) {
    Integer code = userRoleService.deleteUserRole(userId, roleId);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }

  @ApiOperation("修改用户角色")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true, dataType = "Long")
  })
  @PutMapping("/users/{userId}/roles")
  public ResultBean updateUserRole(
      @PathVariable Integer userId,
      @RequestParam("roleIds") @ApiParam(value = "角色 Id 列表") List<Integer> roleIds) {
    Integer code = userRoleService.updateUserRoles(userId, roleIds);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }
}

