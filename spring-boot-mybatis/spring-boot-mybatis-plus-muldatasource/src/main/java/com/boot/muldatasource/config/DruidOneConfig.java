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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

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
	@Autowired
	private Interceptor[] plugins;

	@Bean("oneDataSource")
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(oneParam.getUrl());
		dataSource.setUsername(oneParam.getUsername());
		dataSource.setPassword(oneParam.getPassword());
		dataSource.setDriverClassName(oneParam.getDriverClassName());
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryOne()
			throws Exception {
		MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(resolver.getResources("classpath*:mapper/one/*.xml"));
		factory.setPlugins(plugins);
		return factory.getObject();
	}

	@Bean(name = "sqlSessionTemplateOne")
	public SqlSessionTemplate sqlSessionTemplateOne()
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryOne());
	}

}