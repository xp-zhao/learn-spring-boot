package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.RolePowerEntity;
import com.boot.power.mapper.RolePowerMapper;
import com.boot.power.service.RolePowerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class RolePowerServiceImpl extends ServiceImpl<RolePowerMapper, RolePowerEntity> implements
    RolePowerService {

}
