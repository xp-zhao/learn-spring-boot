package com.boot.redis.service;

import com.auth0.jwt.interfaces.Claim;
import com.boot.redis.utils.JwtUtil;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: JWT 测试
 * @Date 2020/3/6
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJWT {

  @Test
  public void sign() {
    System.out.println(JwtUtil.sign("xp"));
  }

  @Test
  public void verify() {
    Map<String, Claim> map = JwtUtil.verifyToken(
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbk5hbWUiOiJ4cCIsImV4cCI6MTU4MzQ4NTQ3NiwidXNlcklkIjoiMSIsImlhdCI6MTU4MzQ4MTg3Nn0.v5HRD1zVlhr4XT7fQsKIf_EJqGPYzayXyUwKulkLY5E");
    System.out.println(map.get("loginName").asString());
  }
}
