package com.boot.flowable.process;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private HistoryService historyService;

    @Test
    public void testQuery() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        System.out.println(runtimeService);
    }

    @Test
    public void testCreatedProcess() {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("leaveTask", "staffId");
//    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", map);
        ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("leave");
        StringBuilder sb = new StringBuilder();
        sb.append("创建请假流程 processId：").append(processInstance1.getId());
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("staffId").orderByTaskCreateTime()
                .desc().list();
        for (Task task : tasks) {
            sb.append("任务taskId:").append(task.getId());
        }
    }

    @Test
    public void testStartProcessInstanceByKey() {
//    ProcessInstance leave = runtimeService.startProcessInstanceById("leave");
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("leave");
        System.out.println(leave.getId() + "," + leave.getActivityId());
    }

    @Test
    public void testQueryMyTask() {
        String assignee = "lisi";
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
        list.forEach(task -> {
            System.out.println("taskId:" + task.getId() + ",taskName:" + task.getName());
        });
    }

    @Test
    public void testCompleteMyTask() {
        taskService.complete("87b53e5f-7f52-11eb-810e-acde48001122");
    }

    @Test
    public void testQueryHistory() {
        HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId("b0a29c2f-7f50-11eb-a906-acde48001122").singleResult();
        System.out.println("流程定义ID：" + result.getProcessDefinitionId());
        System.out.println("流程实例ID：" + result.getId());
        System.out.println(result.getStartTime());
        System.out.println(result.getEndTime());
    }
}
