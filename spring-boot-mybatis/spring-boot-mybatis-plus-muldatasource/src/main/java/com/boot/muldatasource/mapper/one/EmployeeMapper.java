package com.boot.muldatasource.mapper.one;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.muldatasource.entity.one.Employee;
import org.springframework.stereotype.Repository;

/**
 * 操作数据库接口
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}
