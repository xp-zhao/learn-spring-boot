package com.boot.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.mybatis.entity.Order;

/**
 * OrderService.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/14
 **/
public interface OrderService extends IService<Order> {
    /**
     * 插入和回滚
     *
     * @param order 订单对象
     */
    void insertAndRollback(Order order);

    /**
     * 调用插入方法
     *
     * @param order 订单对象
     */
    void invokeMethod(Order order);

    /**
     * 通过 AopContext 调用方法
     *
     * @param order 订单对象
     */
    void invokeWithAopContext(Order order);

    /**
     * 通过服务自身调用
     *
     * @param order 订单对象
     */
    void invokeWithSelf(Order order);

    /**
     * 通过 application context 调用
     *
     * @param order 订单对象
     */
    void invokeWithAC(Order order);
}