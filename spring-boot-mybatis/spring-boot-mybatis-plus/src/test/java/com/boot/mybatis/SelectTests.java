package com.boot.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.mybatis.entity.Order;
import com.boot.mybatis.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SelectTests.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/10/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectTests {

  @Autowired
  private OrderService orderService;

  @Test
  public void testOrder() {
    System.out.println(orderService.list());
    QueryWrapper wrapper = new QueryWrapper().select("distinct order_id");
    List<Order> orders = orderService.list(wrapper);
    List<String> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
    System.out.println(orderIds);
  }

  @Test
  public void testUpdateById() {
    System.out.println(orderService.getById("12310"));
    Order order = Order.builder()
        .orderId("12310")
        .lastName("xp")
        .build();
    orderService.updateById(order);
    System.out.println(orderService.getById("12310"));
  }

  @Test
  public void testPage(){
    IPage<Order> page = new Page<>(1, 2);
    page = orderService.page(page);
    System.out.println(page.getRecords());
  }
}