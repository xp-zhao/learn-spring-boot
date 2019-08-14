package com.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mybatis.entity.Order;
import com.boot.mybatis.mapper.OrderMapper;
import com.boot.mybatis.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/14
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}