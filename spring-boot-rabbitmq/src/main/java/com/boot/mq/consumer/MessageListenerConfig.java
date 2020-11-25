package com.boot.mq.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: 消息监听器配置
 * @Date 2020-11-25
 **/
@Configuration
public class MessageListenerConfig {

  @Autowired
  private CachingConnectionFactory factory;
  @Autowired
  private MyAckReceiver ackReceiver;

  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
    container.setConcurrentConsumers(1);
    container.setMaxConcurrentConsumers(1);
    // RabbitMQ默认是自动确认，这里改为手动确认消息
    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    //设置一个队列
    container.setQueueNames("TopicQueue");
    container.setMessageListener(ackReceiver);
    return container;
  }
}
