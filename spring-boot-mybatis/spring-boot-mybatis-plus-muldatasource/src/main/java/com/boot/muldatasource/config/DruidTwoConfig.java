package com.boot.muldatasource.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private Interceptor[] plugins;

  @Bean("twoDataSource")
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(twoParam.getUrl());
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
    factory.setPlugins(plugins);
    return factory.getObject();
  }

  @Bean(name = "sqlSessionTemplateTwo")
  public SqlSessionTemplate sqlSessionTemplateTwo()
      throws Exception {
    return new SqlSessionTemplate(sqlSessionFactoryTwo());
  }

}