package com.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mybatis.entity.SingleTable;
import com.boot.mybatis.mapper.SingleTableMapper;
import com.boot.mybatis.service.SingleTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020/4/7
 **/
@Slf4j
@Service
public class SingleTableServiceImpl extends ServiceImpl<SingleTableMapper, SingleTable>
    implements SingleTableService {

}
