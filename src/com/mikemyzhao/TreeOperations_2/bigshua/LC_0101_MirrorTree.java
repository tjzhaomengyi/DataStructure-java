package com.mikemyzhao.TreeOperations_2.bigshua;


/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 9:52
 * @Description:
 */
public class LC_0101_MirrorTree {
  public static class TreeNode {
    int val;
   TreeNode left;
    TreeNode right;
  }

  public boolean isSymmetric(TreeNode root){
    return isMirror(root,root);
  }
  public static boolean isMirror(TreeNode h1, TreeNode h2){
    if(h1 == null && h2 == null){
      return true;
    }
    if(h1 != null && h2 != null){
      return h1.val == h2.val &&
          isMirror(h1.left,h2.right) &&
          isMirror(h1.right,h2.left);
    }
    return false;
  }
}
