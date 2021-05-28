package com.boot.oauthserver.controller;

import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2021-5-28
 **/
@RestController
@RequestMapping("/server")
@Slf4j
public class LoginController {

  @PostMapping("/login")
  public String login(String username, String password, HttpServletResponse response) {
    log.info("用户登录, username: {}", username);
    return "success";
  }
}
