package com.boot.mybatis;

import com.boot.mybatis.entity.Order;
import com.boot.mybatis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TransitionTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void insert() {
        Order order = Order.builder()
                .orderId("1")
                .build();
        orderService.save(order);
        System.out.println(orderService.list());
    }

    @Test
    public void transactionsTest() {
        Order order = Order.builder().orderId("2").build();
        try {
            orderService.insertAndRollback(order);
        } catch (Exception e) {
            log.warn("catch an exception");
        }
        System.out.println(orderService.list());
        orderService.invokeMethod(order);
        System.out.println(orderService.list());
    }

    @Test
    public void testInvokeWithSelf() {
        Order order = Order.builder().orderId("2").build();
        try {
            orderService.invokeWithSelf(order);
        } catch (Exception e) {
            log.warn("catch an exception");
        }
        System.out.println(orderService.list());
    }

    @Test
    public void testInvokeWithAC() {
        Order order = Order.builder().orderId("2").build();
        try {
            orderService.invokeWithAC(order);
        } catch (Exception e) {
            log.warn("catch an exception");
        }
        System.out.println(orderService.list());
    }

    @Test
    public void testInvokeWithAopContext() {
        Order order = Order.builder().orderId("2").build();
        try {
            orderService.invokeWithAopContext(order);
        } catch (Exception e) {
            log.warn("catch an exception");
        }
        System.out.println(orderService.list());
    }

    @Test
    public void testQueryAll() {
        System.out.println(orderService.list());
    }
}
