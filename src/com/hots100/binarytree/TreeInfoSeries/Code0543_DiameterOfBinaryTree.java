package com.hots100.binarytree.TreeInfoSeries;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-03 6:24 下午
 * @Description:树的直径问题
 */
public class Code0543_DiameterOfBinaryTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public class Info{
    public int max_distance;
    public int height;
    public Info(int m, int h){
      max_distance = m;
      height = h;
    }
  }
  public int diameterOfBinaryTree(TreeNode root) {
    return process(root).max_distance;
  }

  public Info process(TreeNode node){
    if(node == null){return new Info(0,0);}
    Info leftInfo = process(node.left);
    Info rightInfo = process(node.right);
    int height = Math.max(leftInfo.height, rightInfo.height) + 1;
    int p1 = leftInfo.max_distance;
    int p2 = rightInfo.max_distance;
    int p3 = leftInfo.height + rightInfo.height + 1;//LC上这里少了一个加1，[1,4,5,3]就是表示直径长度为3
    int maxDistance = Math.max(p1, Math.max(p2, p3));
    return new Info(maxDistance, height);
  }


}
