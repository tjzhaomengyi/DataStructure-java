package com.point2offerspecial.fourteen_dp.bag_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 09:22
 * @Description:注意这题描述返回-1是个幌子，其实如果没有办法应该返回的就是最大值Integer.max
 */
public class PTOS103_CoinsChange {
  //正向思路：和左神322解法的反推想法有点差别，但是大致一样
  public int coinChange(int[] coins, int amount) {
    if (coins == null || (coins.length == 0 && amount > 0)) return -1;
    int N = coins.length;
    int[][] dp = new int[N + 1][amount + 1];//i表示用几种纸币， amount表示凑的钱数
    for (int i = 0; i < N + 1; i++) {
      dp[i][0] = 0;
    }
    //技巧：注意初始化这些凑法都是最大数量的
    for (int i = 0; i < N + 1; i++) {
      for (int j = 1; j < amount + 1; j++) {
        dp[i][j] = Integer.MAX_VALUE;
      }
    }

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < amount + 1; j++) {
        //技巧：嵌套在里面还有一层尝试，就是关于张数的，所以之列不能直接更新dp[i][j],先用一个ans接住
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; coins[i - 1] * zhang <= j; zhang++) {
          if (j - coins[i - 1] * zhang >= 0 && dp[i - 1][j - zhang * coins[i - 1]] != Integer.MAX_VALUE) {
            ans = Math.min(dp[i - 1][j - zhang * coins[i - 1]] + zhang, ans);
          }
        }
        dp[i][j] = ans;
      }
    }

    return dp[N][amount] == Integer.MAX_VALUE ? -1 : dp[N][amount];
  }

  public static void main(String[] args) {
    int[] coins = new int[]{1};
    int target = 2;
    int ans = new PTOS103_CoinsChange().coinChange(coins, target);
    System.out.println(ans);
  }
}
