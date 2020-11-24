package com.boot.mq.consumer;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 消息接收
 * @Date 2020-11-24
 **/
@Component
@RabbitListener(queues = "DirectQueue")
@Slf4j
public class DirectReceiver {

  @RabbitHandler
  public void process(Map msg) {
    log.info("消费者收到消息{}", msg.toString());
  }
}
