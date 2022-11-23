package com.mikemyzhao.TreeOperations;

import static java.lang.Math.max;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 16:41
 * @No: LC124
 * @Description:被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * @Solution:使用后续遍历，左+右+根=max
 */
public class MaxPathSum {
  /**
   Definition for a binary tree node.
   */
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
      //todo:执行有错误
      int ans = Integer.MIN_VALUE;
      public int maxPathSum(TreeNode root){
        if(root == null) return 0;

        int left = max(0,maxPathSum(root.left));
        int right = max(0,maxPathSum(root.right));
        //根节点处理后续遍历
        ans = max(ans,left+right+root.val);
        return max(left,right) + root.val;
      }
 }

}
