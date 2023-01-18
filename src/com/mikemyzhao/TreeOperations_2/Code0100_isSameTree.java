package com.mikemyzhao.TreeOperations_2;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-10 6:10 下午
 * @Description:
 */
public class Code0100_isSameTree {
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    return travser(p,q);
  }

  public boolean travser(TreeNode a, TreeNode b){
    if (a== null ^ b==null) return false;
    if (a == null && b == null) return true;
    return (a.val == b.val) && (travser(a.left, b.left)) && (travser(a.right, b.right));
  }
}
