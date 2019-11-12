package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PowerOperationEntity;
import com.boot.power.mapper.PowerOperationMapper;
import com.boot.power.service.PowerOperationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与功能操作关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerOperationServiceImpl extends
    ServiceImpl<PowerOperationMapper, PowerOperationEntity> implements
    PowerOperationService {

}
