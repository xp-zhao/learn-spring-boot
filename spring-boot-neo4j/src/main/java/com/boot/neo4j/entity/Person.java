package com.boot.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Person.java neo4j 测试对象
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/10
 **/
@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String about;

  public Person(String name, String about) {
    this.name = name;
    this.about = about;
  }

  @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
  public Set<Movie> actors;

  public void addActor(Movie movie) {
    if (actors == null) {
      actors = new HashSet<>();
    }
    actors.add(movie);
  }

  @Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
  public Set<Movie> directors;

  public void addDirector(Movie movie) {
    if (directors == null) {
      directors = new HashSet<>();
    }
    directors.add(movie);
  }
}