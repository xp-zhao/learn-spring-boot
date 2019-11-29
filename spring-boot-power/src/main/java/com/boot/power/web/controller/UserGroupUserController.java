package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.dto.UserGroupUserDTO;
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
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true),
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true),
  })
  @PostMapping("/groups/{groupId}/users/{userId}")
  public ResultBean addUserToUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer userId) {
    ReturnCode returnCode = userGroupUserService.addUserToUserGroup(groupId, userId);
    ResultBean result = new ResultBean();
    result.setCode(returnCode.getCode());
    result.setMsg(returnCode.getMsg());
    return result;
  }

  @ApiOperation("从用户组删除用户")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "groupId", value = "用户组 id", required = true),
      @ApiImplicitParam(name = "userId", value = "用户 id", required = true),
  })
  @DeleteMapping("/groups/{groupId}/users/{userId}")
  public ResultBean deleteUserFromUserGroup(
      @PathVariable Integer groupId,
      @PathVariable Integer userId) {
    ReturnCode returnCode = userGroupUserService.deleteUserFromUserGroup(groupId, userId);
    ResultBean result = new ResultBean();
    result.setCode(returnCode.getCode());
    result.setMsg(returnCode.getMsg());
    return result;
  }

  @ApiOperation("查询指定用户组下所有的用户")
  @ApiImplicitParam(name = "id", value = "用户组 id", required = true)
  @GetMapping("/groups/{id}/users")
  public ResultBean queryAllUserByGroup(@PathVariable Integer id) {
    List<UserGroupUserDTO> result = userGroupUserService.queryAllUserByGroup(id);
    return new ResultBean(result);
  }
}

