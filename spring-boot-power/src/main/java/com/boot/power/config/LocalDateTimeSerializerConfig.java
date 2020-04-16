package com.boot.power.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxiaoping
 * @Description: 时间类型全局格式化配置(注：@JsonFormat注解优先级高于此配置)
 * @Date 2020/4/16
 **/
@Configuration
public class LocalDateTimeSerializerConfig {

  @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
  private String pattern;

  @Bean
  public LocalDateTimeSerializer localDateTimeDeserializer() {
    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
  }
}
