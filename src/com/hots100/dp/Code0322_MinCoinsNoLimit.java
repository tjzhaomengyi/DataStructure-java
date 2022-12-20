package com.hots100.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-02 9:37 下午
 * @Description:
 */
public class Code0322_MinCoinsNoLimit {
  public int coinChange(int[] coins, int amount) {
    if(amount == 0){
      return 0;
    }
    int N = coins.length;
    int[][] dp = new int[N + 1][amount + 1];
    dp[N][0] = 0;
    for(int j = 1; j <= amount; j++){
      dp[N][j] = Integer.MAX_VALUE;
    }
    for(int i = N - 1; i >= 0; i--){
      for(int rest = 0; rest <= amount + 1 ; rest++){//扣边界：使用rest代替amount
        int ans = Integer.MAX_VALUE;
        for(int zhang = 0; zhang * coins[i] <= rest; zhang++){
          int next = dp[i + 1][rest - zhang * coins[i]];
          if(next != Integer.MAX_VALUE){
            ans = Math.min(ans, zhang + next);
          }
        }
        dp[i][rest] = ans;
      }
    }
    return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
  }
}
