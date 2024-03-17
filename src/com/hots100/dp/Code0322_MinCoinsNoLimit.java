package com.hots100.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-02 9:37 下午
 * @Description:
 */
public class Code0322_MinCoinsNoLimit {
  //这种最好理解
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

  //有枚举行为的动态规划
  public int minCoinsDPSimple(int[] arr, int aim){
    if(arr == null || arr.length == 0 || aim < 0){
      return -1;
    }
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    for(int col = 1; col <= aim; col++){
      dp[N][col] = -1;
    }
    for(int i = N - 1; i >= 0; i--){
      for(int rest = 0; rest <= aim; rest++){
        dp[i][rest] = -1;
        //如果之前一张是有效的就可以直接用
        if(dp[i + 1][rest] != -1){
          dp[i][rest] = dp[i + 1][rest];
        }
        //如果左边位置不越界且有效
        if(rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1){
          if(dp[i][rest] == -1){
            dp[i][rest] = dp[i][rest - arr[i]] + 1;//
          } else {
            dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
          }
        }
      }
    }
    return dp[0][aim];
  }

//最终压缩
public static int dp2(int[] arr, int aim) {
  if (aim == 0) {
    return 0;
  }
  int N = arr.length;
  int[][] dp = new int[N + 1][aim + 1];
  dp[N][0] = 0;
  for (int j = 1; j <= aim; j++) {
    dp[N][j] = Integer.MAX_VALUE;
  }
  for (int index = N - 1; index >= 0; index--) {
    for (int rest = 0; rest <= aim; rest++) {
      dp[index][rest] = dp[index + 1][rest];
      if (rest - arr[index] >= 0
              && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
        dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
      }
    }
  }
  return dp[0][aim];
}
}
