package com.boot.security.config;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.common.beans.ResultBean;
import java.io.PrintWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SecurityConfig.java
 * Security 配置类
 *
 * @author: zhaoxiaoping
 * @date: 2020/01/16
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/ignore");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(((req, resp, authException) -> {
          resp.setContentType("application/json;charset=utf-8");
          resp.setStatus(401);
          PrintWriter out = resp.getWriter();
          ResultBean result = ResultBean.error("用户未登录");
          out.append(JSONObject.toJSONString(result));
          out.flush();
          out.close();
        }));

  }
}