package com.boot.neo4j;

import com.boot.neo4j.entity.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TreeController.java neo4j 树形结构测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/25
 **/
@RestController
public class TreeController {

  @Autowired
  private Driver driver;

  @RequestMapping("/getTree")
  public List<TreeNode> tree() {
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    // 获取 小学-数学 的所有一级知识点
    StatementResult result = tx
        .run("match (n:ZSLY) where n.section = '小学' and n.node_id =~ '.*_SX_.*' return n");
    List<TreeNode> nodes = new ArrayList<>();
    while (result.hasNext()) {
      Node node = result.next().get("n").asNode();
      Map<String, Object> map = node.asMap();
      String name = (String) map.get("name");
      String nodeId = (String) map.get("node_id");
      TreeNode node1 = new TreeNode(name, nodeId, (String) map.get("section"),
          (String) map.get("seq"), null);
      System.out.println("-name:" + name);
      System.out.println("-nodeId: " + nodeId);
      StatementResult chlid = tx
          .run("match (n{node_id:'" + nodeId + "'}) - [r:CONTAIN] -> (m) return m");
      List<TreeNode> childs = new ArrayList<>();
      while (chlid.hasNext()) {
        Node node2 = chlid.next().get("m").asNode();
        Map<String, Object> map1 = node2.asMap();
        String name1 = (String) map1.get("name");
        String nodeId1 = (String) node2.asMap().get("node_id");
        TreeNode child = new TreeNode(name1, nodeId1, "", null, null);
        childs.add(child);
        System.out.println("--name:" + name1);
        System.out.println("--nodeId: " + nodeId1);
      }
      node1.setChild(childs);
      nodes.add(node1);
    }
    return nodes;
  }

  @RequestMapping("/getChild/{nodeId}")
  public List<TreeNode> child(@PathVariable(value = "nodeId") String nodeId){
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult chlid = tx
        .run("match (n{node_id:'" + nodeId + "'}) - [r:CONTAIN] -> (m) return m");
    List<TreeNode> childs = new ArrayList<>();
    while (chlid.hasNext()) {
      Node node2 = chlid.next().get("m").asNode();
      Map<String, Object> map1 = node2.asMap();
      String name1 = (String) map1.get("name");
      String nodeId1 = (String) node2.asMap().get("node_id");
      TreeNode child = new TreeNode(name1, nodeId1, "", null, null);
      childs.add(child);
    }
    return childs;
  }

  @RequestMapping("/getName")
  public List<String> getNameList(String name){
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult chlid = tx
        .run("match (n) where n.section = '小学' and n.node_id =~ '.*_SX_.*' and  n.name =~ '.*" + name + ".*' return n limit 5");
    List<String> names = new ArrayList<>();
    while (chlid.hasNext()) {
      Node node2 = chlid.next().get("n").asNode();
      Map<String, Object> map1 = node2.asMap();
      String name1 = (String) map1.get("name");
      names.add(name1);
    }
    return names;
  }
}