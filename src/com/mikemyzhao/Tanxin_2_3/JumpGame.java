package com.mikemyzhao.Tanxin;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 13:03
 * @Description:跳跃游戏题目
 *
 */
public class JumpGame {
/**1、 nums[i]代表能向后跳的最大步数，问能不能跳过
 *  nums=[2,3,1,1,4]，一共4步，true
 * **/
  boolean canJump(int[] nums){
    int farthest =0;
    for(int i=0;i<nums.length;i++){
      //计算能跳的最远距离
      farthest = Math.max(farthest,i+nums[i]);
      if(farthest<=i) return false;
    }
    return farthest>=nums.length-1;
  }

  /**求最小跳跃次数**/
  int[] dp;
  int jumpMin(int[] nums){
    int n = nums.length;
    dp = new int[n];
    Arrays.fill(dp,n);
    return dp(nums,0);
  }

  //返回索引p调到最后一格需要的最小步数
  //这么解时间复杂度不行
  int dp(int[] nums,int p){
    int n = nums.length;
    //base case
    if(p>=n-1){
      return 0;
    }
    //子问题已经计算过
    if(dp[p]!=n){
      return dp[p];
    }
    int steps = nums[p];
    //穷举每一个选择
    for(int i=1;i<=steps;i++){
      //计算每一个子问题的结果
      int subProblem=dp(nums,p+i);
      //取其中最小的作为最终结果
      dp[p]=Math.min(dp[p],subProblem+1);
    }
    return dp[p];
  }

  /**
   * 如果用动态规划方法超时的就可以使用贪心算法来减少时间复杂度
   *
   * **/
  int jumpTanxin(int[] nums){
    int n = nums.length;
    int end =0;//站在索引i，最多能调到索引end
    int farthest =0;//从【i..end】起跳，最远能跳的距离
    int jumps = 0;//记录跳跃次数
    for(int i=0;i<n-1;i++){
      farthest = Math.max(nums[i]+i,farthest);
      if(end==i){
        jumps++;
        end=farthest;
      }
    }
    return jumps;
  }

}
