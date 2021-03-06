package com.boot.mq.provider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaoxiaoping
 * @Description: 发送消息测试
 * @Date 2020-11-24
 **/
@SpringBootTest
public class SendMessageTest {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  public void testDirectSend() {
    String messageId = String.valueOf(UUID.randomUUID());
    String messageData = "test message, hello!";
    String createTime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("bookId", messageId);
    map.put("oprType", messageData);
    map.put("sendTime", createTime);
    //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
    rabbitTemplate.convertAndSend("DirectExchange", "DirectRouting", map);
  }

  @Test
  public void testTopicSend() {
    String createTime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    Map<String, Object> map = new HashMap<>();
    map.put("bookId", String.valueOf(UUID.randomUUID()));
    map.put("oprType", 0);
    map.put("sendTime", createTime);
    String msg = UUID.randomUUID().toString() + "#" + 0 + "#" + createTime;
    //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
    rabbitTemplate.convertAndSend("TopicExchange", "topic.book", msg);
  }
}
