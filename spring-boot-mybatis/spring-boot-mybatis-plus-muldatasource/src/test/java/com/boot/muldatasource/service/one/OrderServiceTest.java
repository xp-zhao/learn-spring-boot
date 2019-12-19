package com.boot.muldatasource.service.one;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.muldatasource.entity.one.Order;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * OrderServiceTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Test
  public void testOrder() {
    List<Order> orders = orderService.list();
    Assertions.assertThat(orders).isNotEmpty();
  }

  @Test
  public void testOrderPage() {
    IPage<Order> page = new Page<>(1, 1);
    page = orderService.page(page);
    Assert.assertEquals(page.getRecords().size(), 1);
  }
}