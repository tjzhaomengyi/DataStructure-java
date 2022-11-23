package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 11:01
 * @Description:一个长度为n的绳子，割开为n段，n段乘积最大是多少
 */
public class CutLine_14_1 {
  public int cuttingRope(int n) {
      int[] dp = new int[n+1];//dp[i]表示i长的绳子阶段m次后的最大乘积,考虑DP[N]=DP[i]*DP[N-i]
      if(n<2)return 1;
      if(n==2)return 1;
      if(n==3)return 2;
      dp[1]=1;dp[2]=2;dp[3]=3;
      for(int i=4;i<=n;i++){
        for(int j=1;j<=i/2;j++){
          //固定住一段的长度
          dp[i]=Math.max(dp[i],dp[j]*dp[i-j]);//不剪的话为j*(i-j),剪的话就是j*dp[i-j]
        }
      }
    return dp[n];
  }
}
