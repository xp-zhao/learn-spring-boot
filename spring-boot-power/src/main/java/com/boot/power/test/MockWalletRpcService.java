package com.boot.power.test;

/**
 * @author zhaoxiaoping
 * @Description: mock 测试
 * @Date 2020-5-27
 **/
public class MockWalletRpcService extends WalletRpcService {

  @Override
  public String moveMoney(String id, Long buyerId, Long sellerId, Double amount) {
    return "2";
  }
}
