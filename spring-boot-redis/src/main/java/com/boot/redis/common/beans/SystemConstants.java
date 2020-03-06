package com.boot.redis.common.beans;

/**
 * 系统常量
 *
 * @author: zhaoxiaoping
 */
public interface SystemConstants {

  /**
   * 认证相关
   */
  interface Auth {

    /**
     * 请求中的 Token
     */
    String TOKEN_KEY = "Authorization";
    /**
     * 请求中的用户对象
     */
    String USER_ENTITY = "user";

    /**
     * token 过期时间 一小时
     */
    long EXPIRE_TIME = 60;
  }
}
