package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.common.validater.ValidateGroup;
import com.boot.power.entity.UserGroupEntity;
import com.boot.power.service.UserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户组表 前端
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "用户组管理相关接口")
@RestController
public class UserGroupController {

  @Autowired
  private UserGroupService userGroupService;

  @ApiOperation("添加新用户组")
  @ApiImplicitParam(name = "userGroup", value = "用户组实体", required = true, dataType = "UserGroupEntity")
  @PostMapping("/users/groups")
  public ResultBean addUserGroup(
      @Validated(value = ValidateGroup.Add.class)
      @RequestBody UserGroupEntity userGroup) {
    Integer code = userGroupService.addUserGroup(userGroup);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    result.setData(userGroup);
    return result;
  }

  @ApiOperation("查询单个用户组")
  @ApiImplicitParam(name = "id", value = "用户组 id", required = true, dataType = "Long")
  @GetMapping("/users/groups/{id}")
  public ResultBean queryUserGroup(@PathVariable Integer id) {
    UserGroupEntity result = userGroupService.getById(id);
    return new ResultBean(result);
  }

  @ApiOperation("查询所有的用户组")
  @GetMapping("/users/groups")
  public ResultBean queryAllUserGroup() {
    List<UserGroupEntity> result = userGroupService.list();
    return new ResultBean(result);
  }

  @ApiOperation("更新用户组信息")
  @ApiImplicitParam(name = "userGroup", value = "用户组实体", required = true, dataType = "UserGroupEntity")
  @PutMapping("/users/groups")
  public ResultBean updateUserGroup(
      @Validated(value = ValidateGroup.Update.class)
      @RequestBody UserGroupEntity userGroup) {
    Integer code = userGroupService.updateUserGroup(userGroup);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    result.setData(userGroup);
    return result;
  }

  @ApiOperation("删除单个用户组")
  @ApiImplicitParam(name = "id", value = "用户组 id", required = true, dataType = "Long")
  @DeleteMapping("/users/groups/{id}")
  public ResultBean deleteUserGroup(@PathVariable Integer id) {
    boolean flag = userGroupService.removeById(id);
    return new ResultBean(flag);
  }
}

