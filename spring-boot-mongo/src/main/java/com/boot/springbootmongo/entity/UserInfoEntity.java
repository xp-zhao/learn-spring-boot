package com.boot.springbootmongo.entity;


import java.io.Serializable;
import java.math.BigInteger;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2019-11-12
 */
@Data
public class UserInfoEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户 id
   */
  @Id
  private BigInteger id;

  /**
   * 用户名称
   */
  private String userName;

  /**
   * 创建时间
   */
  private String createDate;

  /**
   * 更新时间
   */
  private String updateDate;
}
