package com.mikemyzhao.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 8:47
 * @Description:算法小抄和leetcode通用树节点类型
 */
public class TreeNode {
  public int val;//节点存储的值
  public TreeNode left;//指向左侧子节点的指针
  public TreeNode right;//指向右侧子节点的指针

  //构造函数
  public TreeNode(int val){
    this.val = val;
    this.left = null;
    this.right = null;
  }

  public TreeNode getLeft(){
    return this.left;
  }
  public TreeNode getRight(){
    return this.right;
  }
  public List<TreeNode> getNeighbhood(){
    return new ArrayList<>();
  }
  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(4);
    TreeNode node3 = new TreeNode(6);

    node1.val = 10;

    //连接节点
    node1.left = node2;
    node1.right = node3;
  }
}
