package com.boot.security.config;

import com.alibaba.fastjson.JSONObject;
import com.boot.security.common.beans.ResultBean;
import java.io.PrintWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig.java Security 配置类
 *
 * @author: zhaoxiaoping
 * @date: 2020/01/16
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("xp")
        .password("123456")
        .roles("user");
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/ignore");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .successHandler((req, resp, auth) -> {
          resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
          PrintWriter out = resp.getWriter();
          ResultBean result = ResultBean.ok("登录成功");
          out.append(JSONObject.toJSONString(result));
          out.flush();
          out.close();
        })
        .and()
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(((req, resp, authException) -> {
          resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
          resp.setStatus(401);
          PrintWriter out = resp.getWriter();
          ResultBean result = ResultBean.error("用户未登录");
          out.append(JSONObject.toJSONString(result));
          out.flush();
          out.close();
        }))
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler((req, resp, authentication) -> {
          resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
          resp.setStatus(200);
          PrintWriter out = resp.getWriter();
          ResultBean result = ResultBean.ok("注销成功");
          out.append(JSONObject.toJSONString(result));
          out.flush();
          out.close();
        });

  }
}