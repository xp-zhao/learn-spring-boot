package com.boot.power.web.controller;


import com.boot.power.common.beans.ResultBean;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.service.PowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Api(tags = "权限管理相关接口")
@RestController
public class PowerController {

  @Autowired
  private PowerService powerService;

  @ApiOperation("增加新权限")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "name", value = "权限名称", required = true),
      @ApiImplicitParam(name = "type", value = "权限类型", required = true)
  })
  @PostMapping("/powers")
  public ResultBean addPower(String name, Integer type) {
    Integer code = powerService.addPower(name, type);
    ResultBean result = new ResultBean();
    if (code < 0) {
      result.setCode(code);
      result.setMsg(ReturnCode.getMsg(code));
      return result;
    }
    return result;
  }
}

