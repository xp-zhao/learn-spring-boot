package com.boot.jpa;

import com.boot.jpa.entity.Employee;
import com.boot.jpa.entity.RoleEntity;
import com.boot.jpa.entity.UserRoleEntity;
import com.boot.jpa.model.QEmployee;
import com.boot.jpa.model.QRoleEntity;
import com.boot.jpa.model.QUserRoleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description:
 * @Date
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQueryDSL {

  @Autowired
  private JPAQueryFactory factory;

  @Test
  public void testQuery() {
    QEmployee employee = QEmployee.employee;
    Optional<List<String>> result = Optional
        .ofNullable(factory.select(employee.lastName).distinct().from(employee).fetch());
    System.out.println(result.get());
    List<Employee> list = factory.select(employee).from(employee).fetch();
    System.out.println(list);
  }

  @Test
  public void testUserRole() {
    QRoleEntity qr = QRoleEntity.roleEntity;
    QUserRoleEntity qur = QUserRoleEntity.userRoleEntity;
    List<RoleEntity> roleList = factory.select(qr).from(qr).fetch();
    System.out.println(roleList);

    List<UserRoleEntity> adminList = factory.selectFrom(qur)
        .leftJoin(qr).on(qur.roleId.eq(qr.id.stringValue()))
        .where(qr.id.eq(1)).fetch();
    System.out.println(adminList);
  }
}
