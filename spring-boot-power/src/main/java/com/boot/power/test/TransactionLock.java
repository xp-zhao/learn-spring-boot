package com.boot.power.test;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-5-27
 **/
public class TransactionLock {

  public boolean lock(String id) {
    return RedisDistributedLock.getSingletonInstance().lockTransaction(id);
  }

  public void unlock(String id) {
    RedisDistributedLock.getSingletonInstance().unlockTransaction(id);
  }
}
