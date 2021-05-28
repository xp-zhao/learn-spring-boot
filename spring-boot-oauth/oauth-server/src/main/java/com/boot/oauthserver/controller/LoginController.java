package com.boot.oauthserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: 登录控制层
 * @Date 2021-5-28
 **/
@Controller
@RequestMapping("/server")
@Slf4j
public class LoginController {

    private Map<String, String> paramMap = new HashMap<>();

    @GetMapping("/authorize")
    public String login(String responseType, String clientId, String redirectUrl, String scope, String state) {
        paramMap.put("responseType", responseType);
        paramMap.put("clientId", clientId);
        paramMap.put("redirectUrl", redirectUrl);
        paramMap.put("scope", scope);
        paramMap.put("state", state);
        log.info("访问统一登录页面，进行用户登录, param: {}", paramMap);
        return "login";
    }

    @GetMapping("/login")
    public void login(String username, String password, HttpServletResponse response) throws IOException {
        log.info("统一登录服务，账号密码登录成功，重定向到客户端页面");
        response.sendRedirect(paramMap.get("redirectUrl") + "?code=xxx&state=hehe");
    }

    @GetMapping("getTokenByCode")
    @ResponseBody
    public String getTokenByCode(String grantType, String code, String clientId, String redirectUri) throws IOException {
        // 判断client_id、redirect_uri、code是否正确
        log.info("通过校验码获取token");
        // 返回token
        return "{access_token:yyy," +
                "token_type:bearer," +
                "expires_in:600," +
                "refresh_token:zzz}";
    }

    @GetMapping("getUserinfoByToken")
    @ResponseBody
    public String getUserinfoByToken(String token) throws IOException {
        log.info("通过 token 获取用户信息");
        // 判断token是否正确
        return "{userGuid:j3jlk2jj32li43i," +
                "username:Tom," +
                "mobile:18811412324}";
    }
}
