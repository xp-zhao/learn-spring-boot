package com.boot.oauthclient.controller;

import cn.hutool.core.util.StrUtil;
import java.io.IOException;
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
@RequestMapping("/client")
@Slf4j
public class LoginController {

  /**
   * 客户端登录, 重定向到同一登录服务进行登录
   *
   * @param response
   * @throws IOException
   */
  @PostMapping("/login")
  public void login(HttpServletResponse response) throws IOException {
    String serverUrl = "http://localhost:7000/server/login";
    String redirectUrl = "http://localhost:7001/client/index&scope=userinfo&state=hh";
    String url = "{}?response_type={}&client_id={}&redirect_url={}";
    response.sendRedirect(StrUtil.format(url, serverUrl, "code", "10001", redirectUrl));
  }
  
  
}
