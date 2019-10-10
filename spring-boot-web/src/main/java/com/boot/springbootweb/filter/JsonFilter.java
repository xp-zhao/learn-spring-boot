package com.boot.springbootweb.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * JsonFilter.java json 动态过滤
 *
 * @author: zhaoxiaoping
 * @date: 2019/10/10
 **/
public class JsonFilter {

  public static ObjectMapper setupJsonFilter() {
    ObjectMapper mapper = new ObjectMapper();
    String[] beanProperties = new String[]{"address"};
    String nonAddress = "non-address";
    FilterProvider filterProvider = new SimpleFilterProvider()
        .addFilter(nonAddress, SimpleBeanPropertyFilter.serializeAllExcept(beanProperties));
    //serializeAllExcept 表示序列化全部，除了指定字段
    //filterOutAllExcept 表示过滤掉全部，除了指定的字段
    mapper.setFilterProvider(filterProvider);
    return mapper;
  }
}