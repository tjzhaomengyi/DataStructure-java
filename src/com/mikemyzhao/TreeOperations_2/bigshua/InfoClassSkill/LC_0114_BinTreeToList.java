package com.mikemyzhao.TreeOperations_2.bigshua.InfoClassSkill;

import com.MCAAlgorithm.bigshua.class37.Problem_0114_FlattenBinaryTreeToLinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 10:39
 * @Description:
 */
public class LC_0114_BinTreeToList {
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    //技巧:left指向上一个尾部，right指向下一个头部
    public TreeNode(int value) {
      val = value;
    }
  }

  public static TreeNode convert(TreeNode head){
    if(head == null){
      return null;
    }
    return process(head).head;
  }

  public static class Info {
    //技巧:返回给当前节点返回的信息
    public TreeNode head;
    public TreeNode tail;
    public Info(TreeNode h, TreeNode t){
      head = h;
      tail = t;
    }
  }

  public static Info process(TreeNode x){
    if(x == null){
      return null;
    }
    Info leftInfo = process(x.left);
    Info rightInfo = process(x.right);
    //左边
    if (leftInfo != null){
      leftInfo.tail.right = x;
      x.left = leftInfo.tail;

    }
    //右边
    if(rightInfo != null){
      x.right = rightInfo.head;
      rightInfo.head.left = x;
    }
    x.left = null;
    //找到x对应的head和tail
    TreeNode head = leftInfo != null ? leftInfo.head : x;
    TreeNode tail = rightInfo != null ? rightInfo.tail : x;
    return new Info(head, tail);
  }
}
