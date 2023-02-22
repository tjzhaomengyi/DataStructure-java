package com.point2offerspecial.eight_tree.one_dfs;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-08 10:31
 * @Description:
 */
public class PTOS047_PruneTree {
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

  public TreeNode pruneTree(TreeNode root) {
    //技巧：只有当左右子树都为空
    if(root == null) return root;
    root.left = pruneTree(root.left);
    root.right = pruneTree(root.right);
    if(root.left == null && root.right == null && root.val == 0){
      return null;
    }
    return root;
  }

}
