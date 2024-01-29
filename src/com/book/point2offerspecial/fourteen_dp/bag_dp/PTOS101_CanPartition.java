package com.book.point2offerspecial.fourteen_dp.bag_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 19:07
 * @Description:
 */
public class PTOS101_CanPartition {
  public boolean canPartition(int[] nums) {
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
