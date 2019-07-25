package com.boot.neo4j.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TreeNode.java 节点
 *
 * @author: zhaoxiaoping
 * @date: 2019/07/25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TreeNode {

  private String name;
  private String nodeId;
  private String section;
  private String seq;
  private List<TreeNode> child;
}