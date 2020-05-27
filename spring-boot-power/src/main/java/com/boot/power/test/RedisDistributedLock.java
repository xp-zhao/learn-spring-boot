package com.boot.power.test;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-5-27
 **/
public class RedisDistributedLock {

  private static class RedisDistributedLockHolder {

    private static final RedisDistributedLock lock = new RedisDistributedLock();
  }

  public static RedisDistributedLock getSingletonInstance() {
    return RedisDistributedLockHolder.lock;
  }


  public boolean lockTransaction(String id) {
    System.out.println("Transaction is locked. id: " + id);
    return true;
  }

  public boolean unlockTransaction(String id) {
    System.out.println("Transaction is unlocked. id: " + id);
    return true;
  }
}
