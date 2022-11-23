package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import com.MCAAlgorithm.bigshua.class14.Code04_CompleteTreeNodeNumber;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 21:40
 * @Description:求完全二叉树的节点个数
 */
public class CompleteTreeNodeNumbers_14 {
  //完全二叉树的性质，右树高度 - 左树高度 <= 1.
  //求该二叉树的高度，遍历左树的最左侧节点即可
  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  public static int countNodes(Node root){
    if(root == null){
      return 0;
    }
    return bs(root, 1, mostLeftLevel(root,1));//当前第一层，
  }

  //当前节点是node，层数level，总层数h，返回node为头的子树(必须是完全二叉树)，有多少节点
  public static int bs(Node node, int level, int h){
    if(level == h){
      return 1;
    }
    if(mostLeftLevel(node.right,level + 1) == h){
      //这个时候右节点的高度正好等于h，说明左右一边高.左边是满的！
      return (1 << (h - level)) + bs(node.right, level + 1, h);
    }else{//右子树差了一层，左子树有可能没全满
      return (1 << (h - level - 1))+ bs(node.left, level + 1 ,h);
    }
  }
  //当前节点node为头的子树，最大深度是多少
  public static int mostLeftLevel(Node node, int level){
    while(node != null){
      level++;
      node = node.left;
    }
    return level - 1;//因为当前层只要不是null就往下加，所以最后多加了1层
  }
}
