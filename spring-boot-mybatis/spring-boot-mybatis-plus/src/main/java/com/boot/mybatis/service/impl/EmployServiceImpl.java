package com.boot.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.mybatis.entity.Employee;
import com.boot.mybatis.mapper.EmployeeMapper;
import com.boot.mybatis.service.EmployService;
import org.springframework.stereotype.Service;

/**
 * EmployServiceImpl.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/08/27
 **/
@Service
public class EmployServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements
    EmployService {

}