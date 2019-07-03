package com.boot.springbootelastic.dao;

import com.boot.springbootelastic.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * blog实体类操作接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/03
 **/
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

}
