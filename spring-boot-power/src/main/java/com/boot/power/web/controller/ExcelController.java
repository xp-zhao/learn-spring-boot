package com.boot.power.web.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExcelController.java
 *
 * @author: zhaoxiaoping
 * @date: 2019/11/15
 **/
@RestController
@RequestMapping("/excel")
public class ExcelController {

  @GetMapping("/download")
  public void downloadTemplate(
      HttpServletRequest request,
      HttpServletResponse response,
      List<String> names,
      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime dateTime) {
    ExcelWriter writer = ExcelUtil.getWriter(true);
    List<String> head1 = Arrays.asList("公司ID", "公司名称", "任务类型", "任务总额");
    List<String> head2 = Arrays.asList("任务ID", "任务名称", "任务时间");
    List<String> head3 = Arrays.asList("日期", "任务基数", "每日实销份额", "每日实销金额");
    List<List<String>> head = Arrays.asList(head1, head2, head3);
    for (String name : names) {
      ExcelData data = new ExcelData();
      data.setHead(head);
      data.setCompany(Company.builder().id(1).name(name).type("传统渠道").build());
      data.setTask(Task.builder().id(2).name("测试任务").timeLimit("2019/1/1-2019/2/1").build());
      data.setDetails(Arrays.asList(Detail.builder().date("2019-01-01").build(),
          Detail.builder().date("2019-01-02").build()));
      writer.write(data.getHead().get(0));
    }

  }
}

@Data
class ExcelData {

  List<List<String>> head;
  Company company;
  Task task;
  List<Detail> details;

}

@Data
@Builder
class Company {

  Integer id;
  String name;
  String type;
  Integer total;
}

@Data
@Builder
class Task {

  Integer id;
  String name;
  String timeLimit;
}

@Data
@Builder
class Detail {

  String date;
  Integer base;
  String percent;
  Integer total;
}