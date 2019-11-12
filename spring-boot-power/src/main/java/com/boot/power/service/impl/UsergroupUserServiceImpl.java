package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.UsergroupUserEntity;
import com.boot.power.mapper.UsergroupUserMapper;
import com.boot.power.service.UsergroupUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组与用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UsergroupUserServiceImpl extends
    ServiceImpl<UsergroupUserMapper, UsergroupUserEntity> implements
    UsergroupUserService {

}
