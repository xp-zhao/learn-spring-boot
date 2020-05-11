package com.boot.springbootweb.service;

/**
 * @author zhaoxiaoping
 * @Description: 计算策略接口
 * @Date 2020/5/6
 **/
public interface CalStrategy {

  /**
   * 计算方法
   *
   * @param num1
   * @param num2
   * @return
   */
  int calculate(int num1, int num2);
}
