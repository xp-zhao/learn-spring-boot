package com.boot.mybatis;

import com.boot.mybatis.entity.User;
import com.boot.mybatis.mapper.UserMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

  @Autowired
  private UserMapper mapper;

  @Test
  public void testQuery() {
    List<User> users = mapper.selectList(null);
    users.forEach(System.out::println);
  }

  @Test
  public void testInsert() {
    User user = User.builder()
        .name("xp")
        .age(25)
        .email("asdfasf")
        .build();
    int insert = mapper.insert(user);
    System.out.println(insert);
    System.out.println(user);
  }

  @Test
  public void testUpdate() {
    User user = User.builder()
        .id(6L)
        .name("xp1")
        .build();
    int i = mapper.updateById(user);
    System.out.println(i);
    System.out.println(user);
  }
}
