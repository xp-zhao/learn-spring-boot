package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.UserRoleEntity;
import com.boot.power.mapper.UserRoleMapper;
import com.boot.power.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements
    UserRoleService {

}
