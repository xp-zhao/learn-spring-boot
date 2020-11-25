package com.boot.mq.provider;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: topic exchange 配置
 * @Date 2020-11-25
 **/
@Configuration
public class TopicRabbitConfig {

  public static final String book = "book";
  
  @Bean
  public Queue topicQueue(){
    return new Queue("BookQueue");
  }
  
  @Bean
  public TopicExchange topicExchange(){
    return new TopicExchange("BookExchange");
  }
  
  @Bean
  public Binding bindingTopic(){
    return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(book);
  }
}
