package com.pavoindus.xmlparser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 4/28/2018 00:06
 *
 * @author Deepankar Sharma
 */
public class Tree {

  protected Tree() {
  }

  public Tree(String node, String id) {
    this.node = node;
    this.id = id;
  }

  private String id;
  private String node;
  private List<Tree> children = new ArrayList<>();

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public List<Tree> getChildren() {
    return children;
  }

  private void setChildren(List<Tree> children) {
    this.children = children;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
