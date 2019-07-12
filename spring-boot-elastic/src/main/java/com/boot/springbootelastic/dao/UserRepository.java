package com.boot.springbootelastic.dao;

import com.boot.springbootelastic.entity.Blog;

/**
 * 测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/04
 **/
public interface UserRepository extends MyBaseRepository<Blog, String> {

  Blog findByTitle(String title);
}
