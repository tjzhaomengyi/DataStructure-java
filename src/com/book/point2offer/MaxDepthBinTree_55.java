package com.book.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 17:34
 * @Description:
 */
public class MaxDepthBinTree_55 {
  /**二叉树的深度**/
  public int maxDepth_I(TreeNode root) {
    if(root==null) return 0;
    return Math.max(maxDepth_I(root.left),maxDepth_I(root.right))+1;
  }

}
