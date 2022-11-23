package com.mikemyzhao.TreeOperations_2.bigshua.traverse;


/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 10:50
 * @Description:
 */
public class LC_0108_SortedArrayToBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode sortedArrayToBST(int[] nums){
    return process(nums, 0, nums.length - 1);
  }

  //一个二分加前序遍历解决
  public static TreeNode process(int[] arr, int L, int R){
    if(L > R){//技巧:终止条件，解决所有异常点检测
      return  null;
    }
    if(L == R){
      return new TreeNode(arr[L]);
    }
    int M = (L + R) / 2;
    TreeNode head = new TreeNode(arr[M]);
    head.left = process(arr,L, M - 1);
    head.right = process(arr, M + 1, R);//技巧:边界中间节点已经出来了额
    return head;
  }
}
