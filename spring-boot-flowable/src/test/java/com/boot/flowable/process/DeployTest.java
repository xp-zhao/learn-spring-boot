package com.boot.flowable.process;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: xp-zhao
 * @Description: TODO
 * @DateTime: 2021/3/5 9:59 下午
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeployTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void testDeploy(){
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                .category("test-leave")
                .name("请假流程测试")
                .addClasspathResource("processes/test.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy();
        System.out.println(deploy.getId());
    }
}
