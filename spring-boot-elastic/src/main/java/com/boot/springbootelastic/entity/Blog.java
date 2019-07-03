package com.boot.springbootelastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Blog.java 博客实体类
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/03
 **/
@Document(indexName = "website", type = "blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blog {
  @Id
  private String id;
  private String title;
  private String text;
  private String date;
}