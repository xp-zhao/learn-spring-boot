package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PowerMenuEntity;
import com.boot.power.mapper.PowerMenuMapper;
import com.boot.power.service.PowerMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与菜单关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerMenuServiceImpl extends ServiceImpl<PowerMenuMapper, PowerMenuEntity> implements
    PowerMenuService {

}
