package com.boot.power.auth;

import com.boot.power.auth.storage.CredentialStorage;
import com.boot.power.auth.storage.MysqlCredentialStorage;

/**
 * @author zhaoxiaoping
 * @Description: 默认 api 认证实现
 * @Date 2020-5-27
 **/
public class DefaultApiAuthencatorImpl implements ApiAuthencator {

  private CredentialStorage credentialStorage;

  public DefaultApiAuthencatorImpl() {
    this.credentialStorage = new MysqlCredentialStorage();
  }

  public DefaultApiAuthencatorImpl(CredentialStorage credentialStorage) {
    this.credentialStorage = credentialStorage;
  }

  @Override
  public void auth(String url) {
    ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
    auth(apiRequest);
  }

  @Override
  public void auth(ApiRequest apiRequest) {
    String appId = apiRequest.getAppId();
    String token = apiRequest.getToken();
    long timestamp = apiRequest.getTimestamp();
    String originalUrl = apiRequest.getBaseUrl();

    AuthToken clientAuthToken = new AuthToken(token, timestamp);
    if (clientAuthToken.isExpired()) {
      throw new RuntimeException("Token is expired!!!");
    }
    String password = credentialStorage.getPasswordByAppId(appId);
    AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, timestamp, password);
    if (!serverAuthToken.match(clientAuthToken)) {
      throw new RuntimeException("Token verfication failed!!!");
    }
  }
}
