package com.boot.power.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.power.entity.RoleEntity;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
public interface RoleService extends IService<RoleEntity> {
    /**
     * 创建新角色
     *
     * @param role 角色实体
     * @return 返回码
     */
    Integer addRole(RoleEntity role);

    /**
     * 更新角色信息
     *
     * @param role 角色实体
     * @return 返回码
     */
    Integer updateRole(RoleEntity role);
}
