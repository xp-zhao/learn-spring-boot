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
 * DruidOneConfig.java
 * 后台数据库配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/18
 **/
@Configuration
@MapperScan(basePackages = {
    "com.boot.muldatasource.mapper.one"}, sqlSessionTemplateRef = "sqlSessionTemplateOne")
public class DruidOneConfig {

  @Resource
  private OneParam oneParam;

  @Bean("oneDataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(oneParam.getUrl());
    dataSource.setUsername(oneParam.getUsername());
    dataSource.setPassword(oneParam.getPassword());
    dataSource.setDriverClassName(oneParam.getDriverClassName());
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactoryOne()
      throws Exception {
    MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
    factory.setDataSource(dataSource());
    return factory.getObject();
  }

  @Bean(name = "sqlSessionTemplateOne")
  public SqlSessionTemplate sqlSessionTemplateOne()
      throws Exception {
    return new SqlSessionTemplate(sqlSessionFactoryOne());
  }

}