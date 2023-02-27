package com.point2offerspecial.fourteen_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 13:43
 * @Description:
 */
public class PTOS088_ClimbStairs {
  public int minCostClimbingStairs(int[] cost) {
    int[] dp = new int[cost.length + 1];//表示当前位置的最小消耗是多少，目标是跳到cost.len + 1的位置
    //初始时候可以选择0或者1作为开始
    dp[0] = 0;
    dp[1] = 0;
    for(int i = 2; i < cost.length + 1; i++){
      //跳到当前位置可以是从i-1位置过来，也可以从i-2位置过来，过来的时候把对应cost消耗一下
      dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
    }
    return dp[cost.length];
  }
}
