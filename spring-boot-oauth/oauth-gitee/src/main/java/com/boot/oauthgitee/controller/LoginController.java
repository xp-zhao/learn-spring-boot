package com.boot.oauthgitee.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2021-5-29
 **/
@Controller
@RequestMapping("/client")
@Slf4j
public class LoginController {

  @Value("${github.clientId}")
  private String clientId;
  @Value("${github.clientSecrets}")
  private String clientSecrets;

  /**
   * 客户端登录, 重定向到 gitee 授权页面
   *
   * @param response
   * @throws IOException
   */
  @GetMapping("/login")
  public void login(HttpServletResponse response) throws IOException {
    String authorizeUrl = "https://gitee.com/oauth/authorize?client_id={}&redirect_uri={}&response_type=code";
    String redirectUrl = "http://localhost:7001/client/index";
    log.info("跳转到 github 授权页面");
    response.sendRedirect(StrUtil.format(authorizeUrl, clientId, redirectUrl));
  }

  @GetMapping("/index")
  public String index(String code, HttpServletRequest request) {
    log.info("gitee 回调请求, 授权码 code: {}", code);
    String redirectUrl = "http://localhost:7001/client/index";
    String accessTokenUrl = "https://gitee.com/oauth/token?grant_type=authorization_code&redirect_uri={}&client_id={}&client_secret={}&code={}";
    accessTokenUrl = StrUtil.format(accessTokenUrl, redirectUrl, clientId, clientSecrets, code);
    RestTemplate restTemplate = new RestTemplateBuilder().build();
    HttpHeaders postHeader = new HttpHeaders();
    postHeader.setAccept(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
    HttpEntity<String> postEntity = new HttpEntity<>(postHeader);
    ResponseEntity<String> postResponse = restTemplate
        .exchange(accessTokenUrl, HttpMethod.POST, postEntity, String.class);
    log.info("获取授权码返回信息, resp: {}", postResponse.getBody());
    String accessToken = JSONUtil.parseObj(postResponse.getBody()).getStr("access_token");
    log.info("授权码, accessToken: {}", accessToken);
    String getUserUrl = "https://gitee.com/api/v5/user?access_token";
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.AUTHORIZATION, "token " + accessToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response = restTemplate
        .exchange(getUserUrl, HttpMethod.GET, entity, String.class);
    request.getSession().setAttribute("userInfo", response.getBody());
    return "index";
  }

  @PostMapping("getUserInfo")
  @ResponseBody
  public String getUserInfo(HttpServletRequest request) throws Exception {
    Object userInfo = request.getSession().getAttribute("userInfo");
    String name = JSONUtil.parseObj(userInfo).getStr("name");
    String avatarUrl = JSONUtil.parseObj(userInfo).getStr("avatar_url");
    JSONObject jsonObject = new JSONObject();
    jsonObject.putOpt("name", name);
    jsonObject.putOpt("avatarUrl", avatarUrl);
    return JSONUtil.toJsonStr(jsonObject);
  }
}
