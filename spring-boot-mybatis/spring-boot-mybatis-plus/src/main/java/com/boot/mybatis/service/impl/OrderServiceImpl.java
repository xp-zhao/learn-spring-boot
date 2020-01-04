package com.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mybatis.entity.Order;
import com.boot.mybatis.mapper.OrderMapper;
import com.boot.mybatis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderServiceImpl.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/14
 **/
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void insertAndRollback(Order order) {
        save(order);
        throw new RuntimeException();
    }

    @Override
    public void invokeMethod(Order order) {
        try {
            insertAndRollback(order);
        } catch (Exception e) {
            log.warn("catch an exception in invokeMethod()");
        }
    }

    @Override
    public void invokeWithAopContext(Order order) {
        try {
            ((OrderService) AopContext.currentProxy()).insertAndRollback(order);
        } catch (IllegalStateException e) {
            log.warn("catch an exception");
        }
    }

    @Override
    public void invokeWithSelf(Order order) {
        try {
            orderService.insertAndRollback(order);
        } catch (Exception e) {
            log.warn("catch an exception");
        }
    }

    @Override
    public void invokeWithAC(Order order) {
        try {
            ((OrderService) applicationContext.getBean("orderService")).insertAndRollback(order);
        } catch (BeansException e) {
            log.warn("catch an exception");
        }
    }
}