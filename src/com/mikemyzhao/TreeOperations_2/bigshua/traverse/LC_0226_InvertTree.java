package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import com.MCAAlgorithm.bigshua.class37.Problem_0226_InvertBinaryTree;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 11:40
 * @Description:
 */
public class LC_0226_InvertTree {
  public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  }
  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
   TreeNode left = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(left);
    return root;
  }
}
