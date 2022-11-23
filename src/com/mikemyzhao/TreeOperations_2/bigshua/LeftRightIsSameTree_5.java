package com.mikemyzhao.TreeOperations_2.bigshua;


import com.MCAAlgorithm.bigshua.class05.Hash;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 10:21
 * @Description:// 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树
 * // 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
 */
public class LeftRightIsSameTree_5 {
  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }

  //最硬的方法O(N*logN)，判断左右子树的num是否相等，结构是否相等
  public static int sameNumber(Node head){
    if(head == null){
      return 0;
    }
    return sameNumber(head.left) + sameNumber(head.right) + (same(head.left, head.right) ? 1 : 0);
  }

  public static boolean same(Node h1, Node h2) {
    if(h1 == null ^ h2 == null){ //秀一下h1和h2有一个为空！
      return false;
    }
    if(h1 == null && h2 == null){
      return true;
    }
    //如果两个都不是空，保证h1和h2值相等且结构一样！
    return h1.value == h2.value && same(h1.left,h2.left) && same(h1.right,h2.right);
  }

  //使用Info结构,只要统计左右节点的信息，左右节点的信息就是所有节点的hash以及大小
  public static class Info{
    public int ans;
    public String str;
    public Info(int a, String s){
      ans = a;
      str = s;
    }
  }

  public static int sameNumber2(Node head){
    String algorihm = "SHA-256";
    Hash hash  = new Hash(algorihm);
    return process(head,hash).ans;
  }
  public static Info process(Node head, Hash hash){
    if(head == null){
      return new Info(0,hash.hashCode("#"));
    }
    Info l = process(head.left,hash);
    Info r = process(head.right,hash);
    int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
    String str = hash.hashCode(head.value + "," + l.str + r.str);
    return new Info(ans ,str);
  }
}
