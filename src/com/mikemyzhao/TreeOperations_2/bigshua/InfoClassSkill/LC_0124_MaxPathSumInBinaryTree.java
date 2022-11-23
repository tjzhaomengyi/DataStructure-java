package com.mikemyzhao.TreeOperations_2.bigshua.InfoClassSkill;

import com.MCAAlgorithm.bigshua.class30.Problem_0124_BinaryTreeMaximumPathSum;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 12:31
 * @Description:
 */
public class LC_0124_MaxPathSumInBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v) {
      val = v;
    }

    //技巧:任何一棵树，必须汇报上来的信息:最大路径和，存在当前head的路径中的最大路径和
    public static class Info{
      public int maxPathSum;//当前节点的最大路径
      public int maxPathSumFromHead;//当前的最大路径是从当前节点开始的，只有从当前节点向左右分别扎的时候

      public Info(int path, int head){
        maxPathSum = path;
        maxPathSumFromHead = head;//肯定包含头的
      }
    }

    public static int maxPathSum(TreeNode root){
      if(root == null){
        return 0;
      }
      return process(root).maxPathSum;
    }

    public static Info process(TreeNode x){
      if(x == null){
        return null;
      }
      Info leftInfo = process(x.left);
      Info rightInfo = process(x.right);
      //技巧:情况1考虑x节点算进去的可能,注意这里如果x扎向了左右节点，那么左右节点必须也得包含自己的head
      int maxPathSumFromHead = x.val;
      if(leftInfo != null){//技巧:情况1-1:x扎向了左节点,只向左扎，不向右扎
        maxPathSumFromHead = Math.max(maxPathSumFromHead,x.val + leftInfo.maxPathSumFromHead);
      }
      if(rightInfo != null){//技巧:情况1-2:x扎向了右节点，只向右扎，不向左扎
        maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + rightInfo.maxPathSumFromHead);
      }

      //技巧:情况2考虑x节点不算进去的可能，不包含x节点
      int maxPathSum = x.val;
      if(leftInfo != null){
        maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
      }
      if(rightInfo != null){
        maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
      }

      //技巧:情况1和2结束了，先做个了结，给出暂时最大的路径
      maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);
      //技巧:情况3.x节点在中间左右两侧贯穿
      if(leftInfo != null && rightInfo != null && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0){
        //技巧:当前情况maxpathsum和贯穿之后的比一下
        maxPathSum = Math.max(maxPathSum,leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead + x.val);
      }

      return new Info(maxPathSum, maxPathSumFromHead);
    }
  }
}
