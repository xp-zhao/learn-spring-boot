package com.boot.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 手动确认消息
 * @Date 2020-11-25
 **/
@Component
@Slf4j
public class MyAckReceiver implements ChannelAwareMessageListener {

  @Override
  public void onMessage(Message message, Channel channel) 
      throws Exception {
    long deliveryTag = message.getMessageProperties().getDeliveryTag();
    String msg = message.toString();
    log.info("消费者收到消息： {}", msg);
    log.info("消息来源：{}", message.getMessageProperties().getConsumerQueue());
    // 手动确认
    channel.basicReject(deliveryTag, false);
  }
}
