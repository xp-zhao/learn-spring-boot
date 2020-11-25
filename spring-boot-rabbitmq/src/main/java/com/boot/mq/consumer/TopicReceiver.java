package com.boot.mq.consumer;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description: 消息接收
 * @Date 2020-11-24
 **/
@Component
//@RabbitListener(queues = "TopicQueue")
@Slf4j
public class TopicReceiver {

//  @RabbitHandler
  public void process(Map msg) {
    /**
     * bookId: 书本 id
     * oprType: 0, 0:新增，1：删除，2：修改
     * sendTime: yyyy-MM-dd HH:mm:ss
     */
    log.info("消费者收到消息{}", msg.toString());
  }
}
