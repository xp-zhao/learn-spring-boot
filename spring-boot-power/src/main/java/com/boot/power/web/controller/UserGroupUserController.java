package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.dto.UserGroupUserDTO;
import com.boot.power.service.UserGroupUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户组 id", required = true),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示的数量")
    })
    @GetMapping("/groups/{id}/users")
    public ResultBean queryAllUserByGroup(@PathVariable Integer id, Integer pageNum, Integer pageSize) {
        List<UserGroupUserDTO> result = userGroupUserService.queryAllUserByGroup(id, pageNum, pageSize);
        return new ResultBean(result);
    }
}

