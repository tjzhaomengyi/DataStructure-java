package com.mikemyzhao.DP_5;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-09 9:49
 * @Description:打家劫舍类问题
 */
public class Rob {
  /**小偷从开始偷到末尾，但是相邻的不能连着偷，求偷得最大**/
  int rob(int[] nums){
    int n = nums.length;
    int dp[] = new int[n+2];
    //dp[i]表示在该位置能偷得的最大值
    for(int i=n-1;i>=0;i--){
      //这家能偷表明上一家没偷，所以要拿dp[i+2]的值;这家不能偷说明上一家偷了
      dp[i]=Math.max(dp[i+2]+nums[i],dp[i+1]);
    }
    return dp[0];
  }
  /**第一间房子和最后一间是连着的不能，取了第一个就不能取最后一个
   *
   *
   * 3   0   2   2   3   5
   * 偷                  不偷 从0到n-2计算
   * 不偷                 偷  从1到n-1计算
   * **/
  int robCycle(int[] nums){
    int n = nums.length;
    if(n==1) return nums[0];
    int[] nums1 = new int[n-1];//偷第一家，不偷最后一家
    int[] nums2 = new int[n-1];//不偷第一家，偷最后一家
    System.arraycopy(nums,0,nums1,0,n-1);//
    System.arraycopy(nums,1,nums2,0,n-1);
    return Math.max(rob(nums1),rob(nums2));
  }

  /**家舍是二叉树结构，连着的不能偷，也就是偷了父节点，左右两个子节点就不能偷了**/
  int robTree(TreeNode root){

    //添加备忘录，记录某个节点的最优选择
    Map<TreeNode,Integer> memo = new HashMap<TreeNode,Integer>();
    if(root == null){return 0;}
    //表示这家偷过了
    if(memo.containsKey(root)) {
      return memo.get(root);
    }
    /**下面这样写法在LeetCode上时间不行,但是用JS就可以**/
    //偷这家
    int do_it = root.val
        + (root.left==null ? 0:robTree(root.left.left)+robTree(root.left.right))
        + (root.right==null?0:robTree(root.right.left)+robTree(root.right.right));
    //不取这家
    int not_do = robTree(root.left)+robTree(root.right);
    int res = Math.max(do_it,not_do);
    memo.put(root,res);
    return res;
  }
  int robTreeByInternal(TreeNode root){
    HashMap<TreeNode,Integer> memo = new HashMap<TreeNode,Integer>();
    return robInternal(root,memo);
  }
  private int robInternal(TreeNode root,HashMap<TreeNode,Integer> memo){
    if(root == null) return 0;
    if(memo.containsKey(root)){
      return memo.get(root);
    }
    int money = root.val;
    //这root偷，后续只能偷孙子的，不能偷儿子的，两种情况
    /**这样money算出来就是偷了该节点和孙子的**/
    if(root.left!=null){
      money+=robInternal(root.left.left,memo)+robInternal(root.left.right,memo);
    }
    if(root.right!=null){
      money+=robInternal(root.right.left,memo)+robInternal(root.right.right,memo);
    }
    int result = Math.max(money,robInternal(root.left,memo)+robInternal(root.right,memo));
    memo.put(root,result);
    return result;
  }

  public static void main(String[] args) {
    System.out.println(new Rob().rob(new int[]{1,2,3,1}));
    System.out.println();
  }
}
