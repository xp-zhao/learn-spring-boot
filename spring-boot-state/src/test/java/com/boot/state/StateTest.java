package com.boot.state;

import com.boot.state.enums.Events;
import com.boot.state.enums.States;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2021-6-19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StateTest {

  @Resource
  private StateMachine<States, Events> stateMachine;

  @Test
  public void testState() {
    stateMachine.start();
    stateMachine.sendEvent(Events.PAY);
    stateMachine.sendEvent(Events.RECEIVE);
  }
}
