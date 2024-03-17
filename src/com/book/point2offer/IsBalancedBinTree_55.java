package com.book.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 16:52
 * @Description:
 */
public class IsBalancedBinTree_55 {
  public boolean isBalanced(TreeNode root) {
    if(root==null) return false;
    return (Math.abs(depth(root.left)-depth(root.right))<=1)
        && isBalanced(root.left) && isBalanced(root.right);
  }

  public int depth(TreeNode root){
    if(root==null) return 0;
    return Math.max(depth(root.left),depth(root.right));
  }
}
