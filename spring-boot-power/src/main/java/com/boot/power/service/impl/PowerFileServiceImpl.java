package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PowerFileEntity;
import com.boot.power.mapper.PowerFileMapper;
import com.boot.power.service.PowerFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与文件关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerFileServiceImpl extends ServiceImpl<PowerFileMapper, PowerFileEntity> implements
    PowerFileService {

}
