package com.book.point2offerspecial.eight_tree.three_binary_search_tree;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-11 17:37
 * @Description:
 */
public class PTOS054_ConvertBST {
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
