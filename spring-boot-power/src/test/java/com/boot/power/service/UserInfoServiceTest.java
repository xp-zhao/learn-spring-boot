package com.boot.power.service;

import com.boot.power.dto.UserGroupUserDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * UserInfoServiceTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {

  @Autowired
  private UserInfoService userinfoService;
  @Autowired
  private UserGroupUserService userGroupUserService;

  @Test
  public void testUserList(){
    System.out.println(userinfoService.list());
  }

  @Test
  public void testGroupUser(){
    PageHelper.startPage(1, 1);
    List<UserGroupUserDTO> list = userGroupUserService.queryAllUserByGroup(1, null, null);
    PageInfo<UserGroupUserDTO> page = new PageInfo<>(list);
    System.out.println(list);
    System.out.println(page.getList());
  }
}