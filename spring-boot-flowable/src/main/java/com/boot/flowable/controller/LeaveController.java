package com.boot.flowable.controller;

import java.util.HashMap;
import java.util.List;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxiaoping
 * @Description: 请假控制层
 * @Date 2021-3-3
 **/
@RestController
@RequestMapping("/leave")
public class LeaveController {

  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private RepositoryService repositoryService;
  @Autowired
  private ProcessEngine processEngine;

  /**
   * 启动流程
   *
   * @param staffId
   * @return
   */
  @RequestMapping(value = "startLeaveProcess")
  @ResponseBody
  public String startLeaveProcess(String staffId) {
    HashMap<String, Object> map = new HashMap<>(1);
    map.put("leaveTask", staffId);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Leave", map);
    StringBuilder sb = new StringBuilder();
    sb.append("创建请假流程 processId：").append(processInstance.getId());
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(staffId).orderByTaskCreateTime()
        .desc().list();
    for (Task task : tasks) {
      sb.append("任务taskId:").append(task.getId());
    }
    return sb.toString();
  }

  @RequestMapping(value = "applyTask")
  @ResponseBody
  public String applyTask(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      throw new RuntimeException("流程不存在");
    }
    HashMap<String, Object> map = new HashMap<>();
    map.put("checkResult", "通过");
    taskService.complete(taskId, map);
    return "申请审核通过~";
  }

  @ResponseBody
  @RequestMapping(value = "rejectTask")
  public String rejectTask(String taskId) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("checkResult", "驳回");
    taskService.complete(taskId, map);
    return "申请审核驳回~";
  }

//  @RequestMapping(value = "createProcessDiagramPic")
//  public void createProcessDiagramPic(HttpServletResponse httpServletResponse, String processId)
//      throws Exception {
//
//    ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId)
//        .singleResult();
//    if (pi == null) {
//      return;
//    }
//    Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//
//    String InstanceId = task.getProcessInstanceId();
//    List<Execution> executions = runtimeService
//        .createExecutionQuery()
//        .processInstanceId(InstanceId)
//        .list();
//
//    List<String> activityIds = new ArrayList<>();
//    List<String> flows = new ArrayList<>();
//    for (Execution exe : executions) {
//      List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//      activityIds.addAll(ids);
//    }
//
//    /**
//     * 生成流程图
//     */
//    BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//    ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//    ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//    InputStream in = diagramGenerator
//        .generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(),
//            engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(),
//            1.0);
//    OutputStream out = null;
//    byte[] buf = new byte[1024];
//    int legth = 0;
//    try {
//      out = httpServletResponse.getOutputStream();
//      while ((legth = in.read(buf)) != -1) {
//        out.write(buf, 0, legth);
//      }
//    } finally {
//      if (in != null) {
//        in.close();
//      }
//      if (out != null) {
//        out.close();
//      }
//    }
//  }
}
