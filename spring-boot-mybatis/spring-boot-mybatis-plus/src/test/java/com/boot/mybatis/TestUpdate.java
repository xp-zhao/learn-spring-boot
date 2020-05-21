package com.boot.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.mybatis.entity.User;
import com.boot.mybatis.mapper.UserMapper;
import com.boot.mybatis.service.UserService;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 更新测试
 * @Date 2020/5/21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUpdate {
  
  @Autowired
  private UserService userService;
  
  @Test
  public void testUpdate(){
    Wrapper wrapper = Wrappers.<User>lambdaQuery()
        .eq(User::getId, 7);
    User user = userService.getOne(wrapper);
    System.out.println(user);
    String name = "";
    userService.lambdaUpdate()
        .set(StringUtils.isNotBlank(name), User::getName, name)
        .set(User::getUpdated, LocalDateTime.now())
        .eq(User::getId, 7)
        .update();
  }
}
