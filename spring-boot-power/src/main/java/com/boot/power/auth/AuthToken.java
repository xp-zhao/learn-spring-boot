package com.boot.power.auth;

import java.util.Map;

/**
 * @author zhaoxiaoping
 * @Description: token 相关处理
 * @Date 2020-5-27
 **/
public class AuthToken {

  private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;
  private String token;
  private long createTime;
  private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

  public AuthToken(String token, long createTime) {
    this.token = token;
    this.createTime = createTime;
  }

  public AuthToken(String token, long createTime, long expiredTimeInterval) {
    this.token = token;
    this.createTime = createTime;
    this.expiredTimeInterval = expiredTimeInterval;
  }

  public static AuthToken create(String baseUrl, long createTime, Map<String, String> params) {
    AuthToken authToken = new AuthToken("token", 121232L);
    return authToken;
  }

  public static AuthToken generate(String originalUrl, String appId, long timestamp, String password) {
    return new AuthToken("", 0);
  }

  public String getToken() {
    return this.token;
  }

  public boolean isExpired() {
    return false;
  }

  public boolean match(AuthToken authToken) {
    return true;
  }
}
