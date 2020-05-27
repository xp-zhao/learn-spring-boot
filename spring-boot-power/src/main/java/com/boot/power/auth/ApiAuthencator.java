package com.boot.power.auth;

/**
 * @author zhaoxiaoping
 * @Description: api 认证
 * @Date 2020-5-27
 **/
public interface ApiAuthencator {

  /**
   * 接口认证
   *
   * @param url
   */
  void auth(String url);

  /**
   * 接口认证
   *
   * @param apiRequest
   */
  void auth(ApiRequest apiRequest);
}
