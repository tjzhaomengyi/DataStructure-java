package com.point2offer;


import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-10 21:27
 * @Description:
 */
public class DicesProbabilty_60 {
  public double[] dicesProbability(int n) {
    double[] dp = new double[6];
    Arrays.fill(dp, 1.0 / 6.0);
    //从第二个骰子开始，n表示n个骰子
    for(int i=2;i<=n;i++){
      double[] tmp = new double[5*i+1];
      for(int j=0;j<dp.length;j++){
        for(int k=0;k<6;k++){
          tmp[j+k]+=dp[j]/6.0;
        }
      }
      dp=tmp;
    }
    return dp;
  }
}
