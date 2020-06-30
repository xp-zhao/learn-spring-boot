package com.boot.security.config;

import com.boot.security.common.beans.ResultBean;
import com.boot.security.common.filter.CustomAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    http.authorizeRequests().anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .csrf().disable().exceptionHandling()
        .authenticationEntryPoint((req, resp, authException) -> {
          resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
          PrintWriter out = resp.getWriter();
          ResultBean result = ResultBean.ok("未登录!");
          out.write(new ObjectMapper().writeValueAsString(result));
          out.flush();
          out.close();
        });
    http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
    filter.setAuthenticationSuccessHandler((req, resp, auth) -> {
      resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
      PrintWriter out = resp.getWriter();
      ResultBean result = ResultBean.ok("登录成功!");
      out.write(new ObjectMapper().writeValueAsString(result));
      out.flush();
      out.close();
    });
    filter.setAuthenticationFailureHandler((req, resp, auth) -> {
      resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
      PrintWriter out = resp.getWriter();
      ResultBean result = ResultBean.error("登录失败!");
      out.write(new ObjectMapper().writeValueAsString(result));
      out.flush();
      out.close();
    });
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
  }
}