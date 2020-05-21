package com.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.entity.User;
import com.boot.mybatis.mapper.EmployeeMapper;
import com.boot.mybatis.mapper.UserMapper;
import com.boot.mybatis.service.EmployeeService;
import com.boot.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date 2020/4/7
 **/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}
