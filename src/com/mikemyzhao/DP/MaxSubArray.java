package com.mikemyzhao.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 18:58
 * @Description:最大子数组
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * @NO:Offer42
 */
public class MaxSubArray {
  int maxSubArray(int[] nums){
    int n = nums.length;
    if(n==0) return 0;
    int[] dp = new int[nums.length];
    int dp_0=nums[0];
    int dp_1 = 0,res = dp_0;
    for(int i=1;i<n;i++){
      dp_1=Math.max(nums[i],nums[i]+dp_0);//dp[i]只和dp[i-1]的状态有关，所以可以使用dp_0和dp_1进行“状态压缩”
      dp_0=dp_1;
      res = Math.max(res,dp_1);
    }
    return res;
  }

}
