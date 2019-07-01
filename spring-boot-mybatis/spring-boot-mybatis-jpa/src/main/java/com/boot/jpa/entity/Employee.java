package com.boot.jpa.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Employee.java 数据库表映射实体
 *
 * @author: zhaoxiaoping
 * @date: 2019/06/28
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = -8788743247890574219L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    @Column(columnDefinition = "varchar(100) COMMENT '邮箱地址'")
    private String email;
    private Integer gender;
    private Integer age;
}