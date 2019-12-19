package com.boot.muldatasource.service;

import com.boot.muldatasource.service.one.OrderServiceTest;
import com.boot.muldatasource.service.two.StudentServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * AllTest.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/19
 **/
@RunWith(Suite.class)
@SuiteClasses({OrderServiceTest.class, StudentServiceTest.class})
public class AllTest {

}