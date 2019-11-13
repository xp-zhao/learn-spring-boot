package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.common.validater.ValidateGroup;
import com.boot.power.entity.RoleEntity;
import com.boot.power.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "角色管理相关接口")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色列表")
    @GetMapping("")
    public ResultBean<List<RoleEntity>> getAllRole() {
        return new ResultBean(roleService.list());
    }

    @ApiOperation("查询角色信息")
    @ApiImplicitParam(name = "id", value = "角色 id", required = true)
    @GetMapping("/{id}")
    public ResultBean getRoleById(@PathVariable Integer id) {
        RoleEntity role = roleService.getById(id);
        if (role == null) {
            return new ResultBean(ReturnCode.NO_ROLE.getCode(), ReturnCode.NO_ROLE.getMsg());
        }
        return new ResultBean(role);
    }

    @ApiOperation("创建角色")
    @ApiImplicitParam(name = "role", value = "角色实体", required = true, dataType = "RoleEntity")
    @PostMapping("")
    public ResultBean addRole(
            @Validated(value = ValidateGroup.Add.class)
            @RequestBody RoleEntity role) {
        Integer code = roleService.addRole(role);
        ResultBean result = new ResultBean();
        if (code < 0) {
            result.setCode(code);
            result.setMsg(ReturnCode.getMsg(code));
            return result;
        }
        result.setData(role);
        return result;
    }

    @ApiOperation("更新角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色 id", required = true),
            @ApiImplicitParam(name = "role", value = "角色实体", required = true, dataType = "RoleEntity")
    })
    @PutMapping("/{id}")
    public ResultBean updateRole(
            @PathVariable Integer id,
            @Validated(value = ValidateGroup.Update.class)
            @RequestBody RoleEntity role) {
        Integer code = roleService.updateRole(role);
        ResultBean result = new ResultBean();
        if (code < 0) {
            result.setCode(code);
            result.setMsg(ReturnCode.getMsg(code));
            return result;
        }
        result.setData(role);
        return result;
    }
}

