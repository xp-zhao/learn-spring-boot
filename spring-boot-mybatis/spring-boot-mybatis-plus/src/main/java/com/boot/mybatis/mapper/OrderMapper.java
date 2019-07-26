package com.boot.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * 操作数据库接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
