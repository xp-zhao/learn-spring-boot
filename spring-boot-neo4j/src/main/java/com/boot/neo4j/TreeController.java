package com.boot.neo4j;

import com.boot.neo4j.entity.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TreeController.java neo4j 树形结构测试
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/25
 **/
@Validated
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
  public List<TreeNode> child(@PathVariable(value = "nodeId") String nodeId) {
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
  public List<Map<String, Object>> getNameList(String name) {
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult chlid = tx
        .run(
            "match (n) where n.section = '小学' and n.node_id =~ '.*_SX_.*' and  n.name =~ '.*" + name
                + ".*' return n limit 5");
    List<Map<String, Object>> nodes = new ArrayList<>();
    while (chlid.hasNext()) {
      Node node2 = chlid.next().get("n").asNode();
      Map<String, Object> map1 = node2.asMap();
      nodes.add(map1);
    }
    return nodes;
  }

  @RequestMapping("/add")
  public String add() {
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult result = tx
        .run(
            "create (n:ZSLY:BSDB_SX_KBZSD:RJB_SX_KBZSD{section:'小学',name:'新建一级知识点'}) return id(n)");
    result.next().get("id(n)").asInt();
    return "";
  }

  @RequestMapping("/move")
  public String move(String nodeId, String oprType) {
    // 先获取当前节点的父节点
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    StatementResult result = tx
        .run("match (n{node_id:'RJB_SX_KBZSD_0'}) <- [:CONTAIN] - (m:RJB_SX_KBZSD) return m");
    // 保存跟当前节点处于同级的节点
    List<Map<String, Object>> nodes = new ArrayList<>();
    if (result.hasNext()) {
      // 父节点不为空，不是一级知识点
      Node parent = result.next().get("m").asNode();
      String parentNodeId = (String) parent.asMap().get("node_id");
      StatementResult brother = tx.run(
          "match (n{node_id:'" + parentNodeId + "'}) - [:CONTAIN] -> (m:RJB_SX_KBZSD) return m");
      while (brother.hasNext()) {
        Map<String, Object> map = brother.next().get("m").asMap();
        nodes.add(map);
      }
    } else {
      // 当前节点为一级知识点
      // 获取 小学-数学 的所有一级知识点
      StatementResult brother = tx
          .run("match (n:ZSLY) where n.section = '小学' and n.node_id =~ '.*_SX_.*' return n");
      while (brother.hasNext()) {
        Map<String, Object> map = brother.next().get("n").asMap();
        nodes.add(map);
      }
    }
    /**
     * 确定当前节点的下标
     */
    int index = 0;
    for (int i = 0; i < nodes.size(); i++) {
      if (nodes.get(i).get("node_id").equals(nodeId)) {
        index = i;
      }
    }
    Map<String, String> map = new HashMap<>();
    // 上移
    // 当前节点的位置
    String c1 = (String) nodes.get(index).get("seq");
    if (oprType.equals(1)) {
      // 上一个节点的位置
      String c2 = (String) nodes.get(index - 1).get("seq");
      // 上个节点的 id
      String cn = (String) nodes.get(index - 1).get("node_id");
      map.put(nodeId, c2);
      map.put(cn, c1);
    } else {
      // 下移
      // 下一个节点的位置
      String c2 = (String) nodes.get(index + 1).get("seq");
      // 下一个节点的 id
      String cn = (String) nodes.get(index + 1).get("node_id");
      map.put(nodeId, c2);
      map.put(cn, c1);
    }
    map.forEach((k, v) -> {
      try {
        StatementResult state = tx
            .run("match (n{node_id:'" + k + "'}) set n.seq = '" + v + "' return n");
        state.next().get("n").asMap().forEach((k1, v1) -> {
          System.out.println(k1 + ": " + v1);
        });
      } catch (Exception e) {
        tx.rollbackAsync();
      }
    });
    tx.success();
    tx.rollbackAsync();
    return null;
  }

  @RequestMapping("/remove")
  public String remote(String nodeId) {
    Session session = driver.session();
    Transaction tx = session.beginTransaction();
    tx.run("match (n{node_id:'" + nodeId + "'}) delete n");
    return null;
  }

  @RequestMapping("/nodeInfo")
  public Map<String, Object> getNodeInfo(String nodeId) {
    Session session = driver.session();
    StatementResult result = session.run("match (n{node_id:'" + nodeId + "'}) return n");
    return result.next().get("n").asMap();
  }

  @RequestMapping("/getRestrict")
  public List<Map<String, Object>> getRestrict(String nodeId) {
    Session session = driver.session();
    String label = nodeId.substring(0, nodeId.lastIndexOf("_"));
    StatementResult result = session
        .run("match (n{node_id:'" + nodeId + "'}) <- [:RESTRICT] - (m:" + label + ")  return m");
    List<Map<String, Object>> list = new ArrayList<>();
    while (result.hasNext()) {
      list.add(result.next().get("m").asMap());
    }
    return list;
  }

  @RequestMapping("/getConfuse")
  public List<Map<String, Object>> getConfuse(@NotBlank(message = "nodeId 不能为空") String nodeId) {
    Session session = driver.session();
    String label = nodeId.substring(0, nodeId.lastIndexOf("_"));
    StatementResult result = session.run(
        "match (n{node_id:'" + nodeId + "'}) <- [:CONFUSE] - (m:" + label + ")  return m");
    List<Map<String, Object>> list = new ArrayList<>();
    while (result.hasNext()) {
      list.add(result.next().get("m").asMap());
    }
    return list;
  }
}