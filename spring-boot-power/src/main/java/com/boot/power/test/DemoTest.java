package com.boot.power.test;

import javax.transaction.InvalidTransactionException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020-5-27
 **/
public class DemoTest {

  @MockBean
  private TransactionLock transactionLock;

  @Test
  public void testExecute() throws InvalidTransactionException {
    Long buyerId = 123L;
    Long sellerId = 234L;
    Long productId = 345L;
    Long orderId = 456L;
    Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, 100D) {
      @Override
      public boolean isExpired() {
        return false;
      }
    };
    MockWalletRpcService mockWalletRpcService = new MockWalletRpcService();
    transaction.setWalletRpcService(mockWalletRpcService);
    Mockito.when(transactionLock.lock(Mockito.anyString())).thenReturn(true);
    Mockito.doAnswer(null).when(transactionLock).unlock(Mockito.anyString());
    Mockito.doNothing().when(transactionLock).unlock(Mockito.anyString());
    transaction.setLock(transactionLock);
    boolean execute = transaction.execute();
    Mockito.verify(mockWalletRpcService, Mockito.times(1))
        .moveMoney(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyDouble());
    Assert.assertTrue(execute);
  }
}
