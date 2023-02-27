package com.mikemyzhao.DP.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-08 18:26
 * @Description:子集背包问题，给一个数组判断能否拆成两个子集，使得两个子集的和相等
 * @NO:LC ,目标问题的题解就是dp[N][sum/2],N长度的数组能否拆成sum/2的子串
 */
public class SubBag {
  boolean canPartition(int[] nums){
    if(nums == null || nums.length < 2) return false;
    int len = nums.length;
    int sum = 0;
    for(int i = 0; i < len; i++){
      sum += nums[i];
    }
    if(sum % 2 != 0) return false;
    sum = sum / 2;
    boolean[][] dp = new boolean[len + 1][sum + 1];
    for(int i = 0; i <= len; i++){
      dp[i][0] = true; //用i个凑0肯定可以
    }
    for(int i = 1; i <= len; i++){//要取到len这么多的东西
      for (int j = 1; j <= sum; j++){//目标取到sum
        if(j < nums[i - 1]){ //取的东西超过目标了
          dp[i][j] = dp[i - 1][j];//这个不行
        } else {
          //如果nums[i] <= j那么就可以放进去,选择放或者不放
          //(1)不放，就是[i-1]个东西能不能凑出j
          //(2)放，就是[i]东西放入了，可以凑出j，那么就看看[i-1]个东西的时候能不能凑出[j]
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]; //放进去表示
        }
      }
    }
    return dp[len][sum];
  }

}
