package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.UsergroupRoleEntity;
import com.boot.power.mapper.UsergroupRoleMapper;
import com.boot.power.service.UsergroupRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组与角色表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UsergroupRoleServiceImpl extends
    ServiceImpl<UsergroupRoleMapper, UsergroupRoleEntity> implements
    UsergroupRoleService {

}
