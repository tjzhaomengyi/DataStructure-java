package com.point2offerspecial.eight_tree.two_other_travese;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 16:25
 * @Description:
 */
public class PTOS051_MaxPathSum {

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
  //技巧：使用Info信息，返回每个节点的头部和对应的sum
  class Info {
    int maxPathSum; //当前节点的最大路径
    int maxPathSumFromHead; //当前的最大路径是从当前节点开始的，只有从当前节点向左右分别扎的时候

    public Info(int path, int head) {
      this.maxPathSum = path;
      this.maxPathSumFromHead = head;
    }
  }

    public int maxPathSum(TreeNode root) {
      if(root == null){
        return 0;
      }
      return process(root).maxPathSum;
    }

    public Info process(TreeNode x){
      if(x == null){
        return null;
      }
      Info leftInfo = process(x.left);
      Info rightInfo = process(x.right);
      int maxPathSumFromHead = x.val;//暂时等于自己
      //分别讨论左右子树的情况
      if(leftInfo != null){
        maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + leftInfo.maxPathSumFromHead);
      }
      if(rightInfo != null) {
        maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + rightInfo.maxPathSumFromHead);
      }

      int maxPathSum = x.val;
      if(leftInfo != null){
        maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
      }
      if(rightInfo != null){
        maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
      }

      maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);
      if(leftInfo != null && rightInfo != null
          && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0){
        maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead + x.val);
      }
      return new Info(maxPathSum, maxPathSumFromHead);
    }


}
