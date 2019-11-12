package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PowerEntity;
import com.boot.power.mapper.PowerMapper;
import com.boot.power.service.PowerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, PowerEntity> implements
    PowerService {

}
