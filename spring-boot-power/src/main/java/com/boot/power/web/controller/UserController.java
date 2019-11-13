package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.common.validater.ValidateGroup;
import com.boot.power.entity.UserInfoEntity;
import com.boot.power.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserInfoService userinfoService;

    @ApiOperation("获取所有用户列表")
    @GetMapping("")
    public ResultBean<List<UserInfoEntity>> getAllUser() {
        ResultBean result = new ResultBean();
        result.setData(userinfoService.list());
        return result;
    }

    @ApiOperation("创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "UserInfoEntity")
    @PostMapping("")
    public ResultBean addUser(
            @Validated(value = ValidateGroup.Add.class)
            @RequestBody UserInfoEntity user) {
        Integer code = userinfoService.addUser(user);
        if (code.equals(ReturnCode.REPEAT_USER.getCode())) {
            return new ResultBean(ReturnCode.REPEAT_USER.getCode(), ReturnCode.REPEAT_USER.getMsg());
        }
        return new ResultBean(user);
    }

    @ApiOperation("更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户 id"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "UserInfoEntity")
    })
    @PutMapping("/{id}")
    public ResultBean updateUser(
            @PathVariable("id") Integer id,
            @Validated(value = ValidateGroup.Update.class)
            @RequestBody UserInfoEntity user) {
        Integer code = userinfoService.updateUser(user);
        ResultBean result = new ResultBean();
        if (code < 0) {
            result.setCode(code);
            result.setMsg(ReturnCode.getMsg(code));
            return result;
        }
        result.setData(user);
        return result;
    }

    @ApiOperation(("查询用户信息"))
    @ApiImplicitParam(name = "id", value = "用户 id", required = true)
    @GetMapping("/{id}")
    public ResultBean getUserById(
            @PathVariable("id") @NotNull(message = "用户 id 不能为空", groups = ValidateGroup.Query.class) Integer id) {
        UserInfoEntity user = userinfoService.getById(id);
        if (user == null) {
            return new ResultBean(ReturnCode.NO_USER.getCode(), ReturnCode.NO_USER.getMsg());
        }
        return new ResultBean(user);
    }
}

