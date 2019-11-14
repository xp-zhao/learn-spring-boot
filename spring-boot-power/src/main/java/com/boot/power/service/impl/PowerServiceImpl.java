package com.boot.power.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.power.common.beans.PowerType;
import com.boot.power.common.beans.ReturnCode;
import com.boot.power.entity.PowerEntity;
import com.boot.power.mapper.PowerMapper;
import com.boot.power.service.PowerService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Service
public class PowerServiceImpl extends ServiceImpl<PowerMapper, PowerEntity> implements
    PowerService {

  @Override
  public Integer addPower(String name, Integer type) {
    // 判断是否已有重名的权限
    PowerEntity powerEntity = getOne(
        new LambdaQueryWrapper<PowerEntity>().eq(PowerEntity::getPowerName, name));
    if (powerEntity != null) {
      return ReturnCode.REPEAT_POWER.getCode();
    }
    LocalDateTime now = LocalDateTime.now();
    PowerEntity power = new PowerEntity();
    power.setPowerName(name);
    power.setPowerType(PowerType.getType(type));
    power.setCreateDate(now);
    power.setUpdateDate(now);
    save(power);
    return ReturnCode.SUCCESS.getCode();
  }
}
