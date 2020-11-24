package com.boot.mq.provider;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: direct exchange 配置
 * @Date 2020-11-24
 **/
@Configuration
public class DirectRabbitConfig {

  /**
   * 队列
   */
  @Bean
  public Queue directQueue() {
    return new Queue("DirectQueue", true);
  }

  /**
   * 交换机
   */
  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("DirectExchange", true, false);
  }

  /**
   * 绑定，将队列与交换机绑定
   */
  @Bean
  public Binding bindingDirect() {
    return BindingBuilder.bind(directQueue()).to(directExchange()).with("DirectRouting");
  }
}
