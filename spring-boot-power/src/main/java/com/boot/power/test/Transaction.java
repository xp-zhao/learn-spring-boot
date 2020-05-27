package com.boot.power.test;

import javax.transaction.InvalidTransactionException;
import lombok.Data;

/**
 * @author zhaoxiaoping
 * @Description: 交易类
 * @Date 2020-5-27
 **/
@Data
public class Transaction {

  private String id;
  private Long buyerId;
  private Long sellerId;
  private Long productId;
  private Long orderId;
  private Long createTimestamp;
  private Double amount;
  private STATUS status;
  private String walletTransactionId;
  private WalletRpcService walletRpcService;
  private TransactionLock lock;
  
  public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId,
      Long orderId, Double amount) {
    if (preAssignedId != null && !preAssignedId.isEmpty()) {
      this.id = preAssignedId;
    } else {
//      IdGenerator idGenerator = new SimpleIdGenerator();
      this.id = "123456789";
    }
    if (!this.id.startsWith("t_")) {
      this.id = "t_" + preAssignedId;
    }
    this.buyerId = buyerId;
    this.sellerId = sellerId;
    this.productId = productId;
    this.orderId = orderId;
    this.status = STATUS.TO_BE_EXECUTED;
    this.createTimestamp = System.currentTimeMillis();
    this.amount = amount;
  }

  public boolean execute() throws InvalidTransactionException {
    if ((buyerId == null || (sellerId == null || amount < 0.0))) {
      throw new InvalidTransactionException("...");
    }
    if (status == STATUS.EXECUTED) {
      return true;
    }
    boolean isLocked = false;
    try {
      isLocked = lock.lock(id);
      if (!isLocked) {
        return false;
      }
      if (status == STATUS.EXECUTED) {
        return true;
      }
      if (isExpired()) {
        this.status = STATUS.EXPIRED;
        return false;
      }
      String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
      if (walletTransactionId != null) {
        this.walletTransactionId = walletTransactionId;
        this.status = STATUS.EXECUTED;
        return true;
      } else {
        this.status = STATUS.FAILED;
        return false;
      }
    } finally {
      if (isLocked) {
        lock.unlock(id);
      }
    }
  }
  
  public boolean isExpired(){
    long executionInvokedTimestamp = System.currentTimeMillis();
    if (executionInvokedTimestamp - createTimestamp > 14L * 24 * 3600 * 1000) {
      return true;
    }
    return false;
  }
}
