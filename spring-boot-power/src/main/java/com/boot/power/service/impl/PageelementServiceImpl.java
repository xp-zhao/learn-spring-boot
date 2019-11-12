package com.boot.power.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.entity.PageelementEntity;
import com.boot.power.mapper.PageelementMapper;
import com.boot.power.service.PageelementService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面元素表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PageelementServiceImpl extends
    ServiceImpl<PageelementMapper, PageelementEntity> implements
    PageelementService {

}
