package com.boot.muldatasource.service.two;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.muldatasource.entity.two.StudentEntity;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * StudentServiceTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

  @Autowired
  private StudentService studentService;

  @Test
  public void testStudent() {
    List<StudentEntity> students = studentService.list();
    Assertions.assertThat(students).isNotEmpty();
  }

  @Test
  public void testPage() {
    IPage<StudentEntity> page = new Page<>(1, 1);
    page = studentService.page(page);
    Assert.assertEquals(page.getRecords().size(), 1);
  }
}