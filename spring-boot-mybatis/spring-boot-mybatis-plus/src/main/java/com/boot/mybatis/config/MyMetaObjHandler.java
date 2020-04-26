package com.boot.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date
 **/
@Component
public class MyMetaObjHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("created", LocalDateTime.now(), metaObject);
    this.setFieldValByName("updated", LocalDateTime.now(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updated", LocalDateTime.now(), metaObject);
  }
}
