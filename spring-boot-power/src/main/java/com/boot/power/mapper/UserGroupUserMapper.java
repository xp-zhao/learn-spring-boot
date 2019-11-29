package com.boot.power.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.power.dto.UserGroupUserDTO;
import com.boot.power.entity.UserGroupUserEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户组与用户表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Repository
public interface UserGroupUserMapper extends BaseMapper<UserGroupUserEntity> {
  /**
   * 查询指定用户组下的所有用户
   *
   * @param groupId 用户组 id
   * @return 用户组中的用户列表
   */
  List<UserGroupUserDTO> queryAllUserByGroup(Integer groupId);
}
