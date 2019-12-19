package com.boot.muldatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DruidTwoConfig.java
 * 后台数据库配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/18
 **/
@Configuration
@MapperScan(basePackages = {
    "com.boot.muldatasource.mapper.two"}, sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class DruidTwoConfig {

  @Resource
  private TwoParam twoParam;

  @Bean("twoDataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(twoParam.getUrl());
    dataSource.setUsername(twoParam.getUsername());
    dataSource.setPassword(twoParam.getPassword());
    dataSource.setDriverClassName(twoParam.getDriverClassName());
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactoryTwo()
      throws Exception {
    MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
    factory.setDataSource(dataSource());
    return factory.getObject();
  }

  @Bean(name = "sqlSessionTemplateTwo")
  public SqlSessionTemplate sqlSessionTemplateTwo()
      throws Exception {
    return new SqlSessionTemplate(sqlSessionFactoryTwo());
  }

}