package com.boot.springbootelastic.dao;

import com.boot.springbootelastic.entity.Bank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Bank 索引操作接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/12
 **/
public interface BankRepository extends ElasticsearchRepository<Bank, Integer> {

}
