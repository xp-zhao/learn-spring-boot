package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.RoleEntity;
import com.boot.power.mapper.RoleMapper;
import com.boot.power.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Override
    public Integer addRole(RoleEntity role) {
        // 查询角色是否存在
        RoleEntity entity = getOne(new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getRoleName, role.getRoleName()));
        if (entity != null) {
            return ReturnCode.NO_ROLE.getCode();
        }
        LocalDateTime now = LocalDateTime.now();
        role.setCreateDate(now);
        role.setUpdateDate(now);
        save(role);
        return role.getId();
    }

    @Override
    public Integer updateRole(RoleEntity role) {
        // 判断角色是否存在
        RoleEntity entity = getById(role.getId());
        if (entity == null) {
            return ReturnCode.NO_ROLE.getCode();
        }
        // 查询角色名称是否存在
        RoleEntity newEntity = getOne(new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getRoleName, role.getRoleName())
                .ne(RoleEntity::getId, role.getId()));
        if (newEntity != null) {
            return ReturnCode.REPEAT_ROLE.getCode();
        }
        // 更新角色信息
        role.setUpdateDate(LocalDateTime.now());
        updateById(role);
        return role.getId();
    }
}
