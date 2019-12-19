package com.boot.muldatasource.service.one.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.muldatasource.entity.one.Order;
import com.boot.muldatasource.mapper.one.OrderMapper;
import com.boot.muldatasource.service.one.OrderService;
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