package com.hots100.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-08 5:43 下午
 * @Description:背包问题
 */
public class Code0416_CanPartition {
  //能不能分出两个部分，让两部分和相等
  public boolean canPartition(int[] nums) {
    if(nums == null || nums.length < 2) return false;
    int N = nums.length;
    int sum = 0;
    for(int i = 0; i < nums.length; i++){
      sum += nums[i];
    }
    if(sum % 2 != 0) return false;
    sum = sum / 2;
    //用这个思路不是非常好想，PTOS101使用的是直接用是否能凑一半sum的方法做dp
    int[][] dp = new int[N+1][sum + 1];//拿走N件冻洗能凑出的
    //思路：从i下标的数自由选择，返回累加和尽量接近sum。
    // 在i位置：使用 i位置 的数:arr[i]+process(arr, i+1, rest-arr[i])，
    // 或者不使用 i位置 的数:process(arr, i+1, rest)
    // 技巧：因为 i 位置要依赖 i+1 位置的值，所以要从下往上逆推。
    //  下标为 N 表示都取尽了，没有数可以拿了，所以dp[N][rest]=0

    for(int i = N-1; i >=0 ; i--){
      for(int rest = 0; rest <= sum; rest++){
        //p是在当前位置的和
        //可能性1：不使用nums[i]得到rest
        int p1 = dp[i + 1][rest];
        int p2 = 0;
        if(nums[i] <= rest){
          //可能性2：使用nums[i]来求rest
          p2 = nums[i] + dp[i + 1][rest - nums[i]];
        }
        dp[i][rest] = Math.max(p1, p2);
      }
    }

    return dp[0][sum] == sum;
  }
}
