package com.boot.oauthclient.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2021-5-28
 **/
@Controller
@RequestMapping("/client")
@Slf4j
public class LoginController {

    /**
     * 客户端登录, 重定向到同一登录服务进行登录
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String serverUrl = "http://localhost:7000/server/authorize";
        String redirectUrl = "http://localhost:7001/client/index&scope=userinfo&state=hh";
        String url = "{}?responseType={}&clientId={}&redirectUrl={}";
        log.info("重定向跳转");
        response.sendRedirect(StrUtil.format(url, serverUrl, "code", "10001", redirectUrl));
    }

    @GetMapping("/index")
    public String index(String code, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String accessToken = restTemplate.getForObject("http://localhost:7000/server/getTokenByCode?grant_type=authorization_code&code=xxx&" +
                "redirect_uri=http://localhost:7001/client/index", String.class);
        String username = restTemplate.getForObject("http://localhost:7000/server/getUserinfoByToken?" +
                "access_token=yyy", String.class);
        request.getSession().setAttribute("username",username);
        return "index";
    }

    @PostMapping("getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) throws Exception {
        Object username = request.getSession().getAttribute("username");
        return "Tom 18811311416";
    }

}
