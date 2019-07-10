package com.boot.neo4j.repository;

import com.boot.neo4j.entity.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * Movie 对象操作接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/10
 **/
public interface MovieRepositroy extends CrudRepository<Movie, Long> {

  Movie findByTitle(String title);
}
