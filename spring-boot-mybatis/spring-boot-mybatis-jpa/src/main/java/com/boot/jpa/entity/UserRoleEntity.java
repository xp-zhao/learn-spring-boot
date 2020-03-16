package com.boot.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Table(name = "tbl_user_role")
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = -8788743247890574219L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}