package com.point2offerspecial.eight_tree.one_dfs;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 11:21
 * @Description:
 */
public class PTOS049_SumNumber {
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
    }}
  public int sumNumbers(TreeNode root) {
    int path = 0;
    return dfs(root, path);
  }

  public int dfs(TreeNode node, int path){
    if(node == null){
      return 0;
    }
    path = path * 10 + node.val;
    if(node.left == null && node.right == null){
      return path;
    }
    return dfs(node.left, path) + dfs(node.right, path);
  }
}
