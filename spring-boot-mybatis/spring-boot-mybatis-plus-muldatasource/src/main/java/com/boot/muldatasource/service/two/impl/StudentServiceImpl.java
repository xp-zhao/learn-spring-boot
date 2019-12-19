package com.boot.muldatasource.service.two.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.muldatasource.entity.two.StudentEntity;
import com.boot.muldatasource.mapper.two.StudentMapper;
import com.boot.muldatasource.service.two.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-12-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements
    StudentService {

}
