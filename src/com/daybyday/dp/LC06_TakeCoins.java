package com.daybyday.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-26 09:30
 * @Description:
 */
public class LC06_TakeCoins {
  public int minCount(int[] coins) {
    int N = coins.length;
    int ans = 0;
    for(int i = 0; i < N; i++){
      ans += coins[i] % 2 == 1 ? coins[i] / 2 + 1 : coins[i] / 2;
    }
    return ans;
  }
}
