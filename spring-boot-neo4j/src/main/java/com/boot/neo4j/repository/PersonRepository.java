package com.boot.neo4j.repository;

import com.boot.neo4j.entity.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Person 对象操作接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/10
 **/
public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByName(String name);
}
