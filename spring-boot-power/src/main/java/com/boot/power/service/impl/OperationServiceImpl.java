package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.OperationEntity;
import com.boot.power.mapper.OperationMapper;
import com.boot.power.service.OperationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能操作表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, OperationEntity> implements
    OperationService {

}
