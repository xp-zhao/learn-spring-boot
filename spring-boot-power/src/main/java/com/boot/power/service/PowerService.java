package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.entity.PowerEntity;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface PowerService extends IService<PowerEntity> {

  /**
   * 新增权限
   *
   * @param name 权限名称
   * @param type 权限类型
   * @return 返回码
   */
  Integer addPower(String name, Integer type);
}
