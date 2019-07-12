package com.boot.springbootelastic.dao;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * MyBaseRepository.java 测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/04
 **/
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends
    Repository<T, ID> {

  Optional<T> findById(ID id);

  <S extends T> S save(S entity);
}