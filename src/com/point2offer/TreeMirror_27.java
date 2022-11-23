package com.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 18:56
 * @Description:
 */
public class TreeMirror_27 {
  public TreeNode mirrorTree(TreeNode root) {
    if(root==null) return null;
    TreeNode left = mirrorTree(root.left);
    TreeNode right = mirrorTree(root.right);
    root.left = right;
    root.right = left;
    return root;
  }
}
