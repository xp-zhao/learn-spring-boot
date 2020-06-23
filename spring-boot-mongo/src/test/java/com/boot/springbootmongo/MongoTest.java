package com.boot.springbootmongo;

import com.boot.springbootmongo.entity.UserInfoEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: MongoDB 相关测试
 * @Date 2020-6-19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

  @Autowired
  private MongoTemplate mongoTemplate;
  
  @Test
  public void testQuery(){
    List<UserInfoEntity> all = mongoTemplate.findAll(UserInfoEntity.class, "test");
    System.out.println(all);
  }
}
