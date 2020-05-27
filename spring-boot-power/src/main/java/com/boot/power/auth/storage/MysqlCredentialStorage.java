package com.boot.power.auth.storage;

/**
 * @author zhaoxiaoping
 * @Description: mysql 存储
 * @Date 2020-5-27
 **/
public class MysqlCredentialStorage implements CredentialStorage {

  @Override
  public String getPasswordByAppId(String appId) {
    return "mysql storage";
  }
}
