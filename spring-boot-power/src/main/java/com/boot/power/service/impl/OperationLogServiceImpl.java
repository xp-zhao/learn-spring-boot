package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.OperationLogEntity;
import com.boot.power.mapper.OperationLogMapper;
import com.boot.power.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class OperationLogServiceImpl extends
    ServiceImpl<OperationLogMapper, OperationLogEntity> implements
    OperationLogService {

}
