package com.boot.redis.common.interceptor;

import com.boot.redis.annotation.LoginUser;
import com.boot.redis.common.beans.SystemConstants.Auth;
import com.boot.redis.entity.UserEntity;
import com.boot.redis.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zhaoxiaoping
 * @Description: 登录用户参数解析器 (有@LoginUser注解的方法参数，注入当前登录用户)
 * @Date 2020/3/6
 **/
@Component
@Slf4j
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter param) {
    return param.getParameterType().isAssignableFrom(UserEntity.class)
        && param.hasParameterAnnotation(LoginUser.class);
  }

  @Override
  public Object resolveArgument(MethodParameter param,
      ModelAndViewContainer container, NativeWebRequest request,
      WebDataBinderFactory factory) throws Exception {
    HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);
    String name = getTokenInfo(req);
    UserEntity user = UserEntity.builder().username(name).build();
    // 放到request中
    req.setAttribute(Auth.USER_ENTITY, user);
    return user;
  }

  private String getTokenInfo(HttpServletRequest request) {
    // 获取 token
    String token = request.getHeader(Auth.TOKEN_KEY);
    if (StringUtils.isBlank(token)) {
      // header 中没有就从参数中获取
      token = request.getParameter(Auth.TOKEN_KEY);
    }
    if (StringUtils.isBlank(token)) {
      throw new IllegalArgumentException(Auth.TOKEN_KEY + "(token)不能为空");
    }
    try {
      String username = JwtUtil.verifyToken(token).get("loginName").asString();
      return username;
    } catch (Exception e) {
      log.error("token 解析失败");
    }
    return null;
  }
}
