package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import com.MCAAlgorithm.bigshua.class38.Problem_0617_MergeTwoBinaryTrees;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 13:18
 * @Description:
 */
public class LC_0617_MergeTwoBinaryTree {
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }
  public static TreeNode mergeTree(TreeNode t1, TreeNode t2){
    if(t1 == null){
      return t2;
    }
    if(t2 == null){
      return t1;
    }
    //t1和t2都不null
    TreeNode merge = new TreeNode(t1.val + t2.val);
    merge.left = mergeTree(t1.left,t2.left);
    merge.right = mergeTree(t1.right, t2.right);
    return merge;
  }
}
