package com.boot.springbootelastic;

import com.boot.springbootelastic.entity.Blog;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TemplateTest.java Template 测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {

  @Autowired
  private ElasticsearchTemplate template;

  @Test
  public void searchQuery(){
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.matchAllQuery())
        .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title", "three")))
        .build();

    Page<Blog> results  = template.queryForPage(searchQuery, Blog.class);
    results.getContent().forEach(System.out::println);
  }
}