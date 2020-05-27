package com.boot.power.auth;

/**
 * @author zhaoxiaoping
 * @Description: 接口请求类
 * @Date 2020-5-27
 **/
public class ApiRequest {

  private String baseUrl;
  private String token;
  private String appId;
  private long timestamp;

  public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
    this.baseUrl = baseUrl;
    this.token = token;
    this.appId = appId;
    this.timestamp = timestamp;
  }

  public static ApiRequest createFromFullUrl(String url) {
    ApiRequest apiRequest = new ApiRequest("", "", "", 1L);
    return apiRequest;
  }

  public String getBaseUrl() {
    return this.baseUrl;
  }

  public String getToken() {
    return this.token;
  }

  public String getAppId() {
    return this.appId;
  }

  public long getTimestamp() {
    return this.timestamp;
  }
}
