package com.boot.flowable.process;

import java.util.HashMap;
import java.util.List;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaoxiaoping
 * @Description: 流程相关测试
 * @Date 2021-3-5
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {

  @Qualifier("processEngine")
  @Autowired
  private ProcessEngine processEngine;
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;

  @Test
  public void testQuery() {
    RuntimeService runtimeService = processEngine.getRuntimeService();
    System.out.println(runtimeService);
  }

  @Test
  public void testCreatedProcess() {
    HashMap<String, Object> map = new HashMap<>(1);
    map.put("leaveTask", "staffId");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("app", map);
    StringBuilder sb = new StringBuilder();
    sb.append("创建请假流程 processId：").append(processInstance.getId());
    List<Task> tasks = taskService.createTaskQuery().taskAssignee("staffId").orderByTaskCreateTime()
        .desc().list();
    for (Task task : tasks) {
      sb.append("任务taskId:").append(task.getId());
    }
  }
}
