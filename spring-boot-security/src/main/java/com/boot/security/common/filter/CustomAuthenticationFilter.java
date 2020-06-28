package com.boot.security.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhaoxiaoping
 * @Description: 自定义认证过滤器
 * @Date 2020-6-24
 **/
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
      ObjectMapper mapper = new ObjectMapper();
      UsernamePasswordAuthenticationToken authRequest = null;
      try (InputStream is = request.getInputStream()) {
        mapper.readValue(is, Map.class);
      } catch (IOException e) {

      }
    } else {
      return super.attemptAuthentication(request, response);
    }
    return null;
  }
}
