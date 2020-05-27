package com.boot.power.auth.storage;

/**
 * @author zhaoxiaoping
 * @Description: 存储接口
 * @Date 2020-5-27
 **/
public interface CredentialStorage {

  /**
   * 通过 appId 获取对应的密码
   *
   * @param appId
   * @return
   */
  String getPasswordByAppId(String appId);
}
