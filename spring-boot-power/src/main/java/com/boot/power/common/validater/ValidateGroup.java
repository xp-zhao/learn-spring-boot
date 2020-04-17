package com.boot.power.common.validater;

import javax.validation.groups.Default;

/**
 * 参数验证组
 * <p>
 * ValidateGroup.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/12
 **/
public class ValidateGroup {

  public interface Add extends Default {

  }

  public interface Update extends Default {

  }

  public interface Query extends Default {

  }
}