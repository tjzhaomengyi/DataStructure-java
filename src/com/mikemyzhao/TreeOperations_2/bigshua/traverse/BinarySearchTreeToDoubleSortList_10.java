package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import com.MCAAlgorithm.bigshua.class10.Code04_BSTtoDoubleLinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 16:07
 * @Description:https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * 把二叉搜索树转化为有序双向链表，并在首尾加环
 */
public class BinarySearchTreeToDoubleSortList_10 {
  //中序遍历二叉树

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  public static class Info{
    public Node start;
    public Node end;
    public Info(Node start, Node end){
      this.start = start;
      this.end = end;
    }
  }

  public static Node treeToDoubleList(Node head){
    if(head == null){
      return null;
    }
    Info allInfo = process(head);
    allInfo.end.right = allInfo.start;
    allInfo.start.left = allInfo.end;
    return allInfo.start;
  }

  public static Info process(Node X){
    if(X == null){
      return new Info(null,null);
    }
    Info lInfo = process(X.left);
    Info rInfo = process(X.right);
    //开始连当前节点
    if(lInfo.end != null){
      lInfo.end.right = X;
    }
    //形成双向链表
    X.left = lInfo.end;
    X.right = rInfo.start;
    if(rInfo.start != null){
      rInfo.start.left = X;
    }
    return new Info(lInfo.start != null ? lInfo.start:X,rInfo.end != null ? rInfo.end : X);
  }
}
