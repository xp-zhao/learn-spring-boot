package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.UserinfoEntity;
import com.boot.power.mapper.UserinfoMapper;
import com.boot.power.service.UserinfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, UserinfoEntity> implements
    UserinfoService {

}
