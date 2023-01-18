package com.hots100.binarytree;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-05 3:47 下午
 * @Description:
 */
public class Code0538_SumTree {
  //技巧：利用搜索二叉树反向中序遍历是递增序列的特点
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
  int sum = 0;
  public TreeNode convertBST(TreeNode root) {
    if(root == null) return null;
    convertBST(root.right);
    sum += root.val;
    root.val = sum;
    convertBST(root.left);
    return root;
  }
}
