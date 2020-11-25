package com.boot.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: mq 相关配置
 * @Date 2020-11-25
 **/
@Configuration
@Slf4j
public class RabbitConfig {

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
    // 设置触发回调
    rabbitTemplate.setMandatory(true);
    rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
      log.info("ConfirmCallback: 相关数据 {}", data);
      log.info("ConfirmCallback: 确认情况 {}", ack);
      log.info("ConfirmCallback: 原因 {}", cause);
    });
    rabbitTemplate.setReturnsCallback((returned) -> {
      log.info("ReturnsCallback: 消息 {}", returned.getMessage());
      log.info("ReturnsCallback: 回应码 {}", returned.getReplyCode());
      log.info("ReturnsCallback: 回应信息 {}", returned.getReplyText());
      log.info("ReturnsCallback: 交换机 {}", returned.getExchange());
      log.info("ReturnsCallback: 路由键 {}", returned.getRoutingKey());
    });
    return rabbitTemplate;
  }
}
