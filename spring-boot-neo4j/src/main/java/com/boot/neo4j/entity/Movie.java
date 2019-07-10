package com.boot.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Movie.java neo4j 测试对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Movie {

  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String about;

  public Movie(String title, String about) {
    this.title = title;
    this.about = about;
  }
}