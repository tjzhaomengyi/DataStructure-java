package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 22:22
 * @Description:
 */
public class Fib_10_1 {
  public int fib(int n) {
    if(n<=1) return n;
    int[] dp = new int[n+1];
    dp[0]=0;
    dp[1]=1;
    for(int i=2;i<n+1;i++){
      dp[i]=dp[i-1]+dp[i-2];
      dp[i] %= 1000000007;
    }
    return dp[n];
  }
}
