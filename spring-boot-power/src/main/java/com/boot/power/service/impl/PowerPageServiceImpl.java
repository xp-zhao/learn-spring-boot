package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PowerPageEntity;
import com.boot.power.mapper.PowerPageMapper;
import com.boot.power.service.PowerPageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与页面元素关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerPageServiceImpl extends ServiceImpl<PowerPageMapper, PowerPageEntity> implements
    PowerPageService {

}
