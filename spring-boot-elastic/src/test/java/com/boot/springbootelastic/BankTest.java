package com.boot.springbootelastic;

import com.boot.springbootelastic.dao.BankRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BankTest.java es Bank索引测试类
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTest {

  @Autowired
  private BankRepository repository;

  /**
   * 通过 id 查询
   */
  @Test
  public void getOne(){
    System.out.println(repository.findById(1));
  }

  @Test
  public void searchTest(){
    QueryBuilder builder = QueryBuilders.matchAllQuery().boost(1);
    System.out.println("查询语句：" + builder.toString());
    repository.search(builder).forEach(System.out::println);
  }


}