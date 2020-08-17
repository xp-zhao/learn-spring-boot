package com.boot.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaoxiaoping
 * @Description: 页面控制
 * @Date 2020-7-27
 **/
@Controller
public class PageController {

  @RequestMapping("/index")
  public String index(Model map) {
    map.addAttribute("newWord", "success");
    return "index";
  }

  @RequestMapping("/upload")
  public String upload(Model map) {
    map.addAttribute("newWord", "success");
    return "upload";
  }
}
