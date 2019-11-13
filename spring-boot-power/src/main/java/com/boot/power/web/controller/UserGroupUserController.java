package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.UserGroupUserEntity;
import com.boot.power.service.UserGroupUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户组与用户表 前端
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "用户组与用户关联接口")
@RestController
public class UserGroupUserController {

  @Autowired
  private UserGroupUserService userGroupUserService;

  @ApiOperation("将用户添加到用户组")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true, dataType = "Long"),
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true, dataType = "Long"),
  })
  @PostMapping("/groups/{groupId}/users/{userId}")
  public ResultBean addUserToUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer userId) {
    Integer code = userGroupUserService.addUserToUserGroup(groupId, userId);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }

  @ApiOperation("从用户组删除用户")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true, dataType = "Long"),
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true, dataType = "Long"),
  })
  @DeleteMapping("/groups/{groupId}/users/{userId}")
  public ResultBean deleteUserFromUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer userId) {
    Integer code = userGroupUserService.deleteUserFromUserGroup(groupId, userId);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }

  @ApiOperation("查询所有用户组")
  @GetMapping("/groups")
  public ResultBean queryAllUserGroup() {
    List<UserGroupUserEntity> users = userGroupUserService.list();
    return new ResultBean(users);
  }

  @ApiOperation("查询单个用户组信息")
  @ApiImplicitParam(name = "id", value = "用户组 id", required = true, dataType = "Long")
  @GetMapping("/groups/{id}")
  public ResultBean queryAllUserByUserGroup(@PathVariable Integer id) {
    UserGroupUserEntity result = userGroupUserService.getById(id);
    return new ResultBean(result);
  }
}

