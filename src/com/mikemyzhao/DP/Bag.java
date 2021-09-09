package com.mikemyzhao.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-08 16:07
 * @Description:背包问题,背包容量为W，物品个数为N
 * @NC: LC
 */
public class Bag {
  /**1、物品两个属性wt[i]重量和val[i]价值,求最大能装的价值是多少，目标是dp[N][W],有3个物品时背包容量为N，能装的最大价值**/
  //dp[i][w]表示对于前i个物品，当前背包容量为w时装下最大价值dp[i][w]
  int bag01(int W,int N,int[] wt,int[] vals){
    /**状态转移只会是放和不放**/
    int[][] dp = new int[N+1][W+1];
    for(int i=1;i<=N;i++){//i个物品
      for(int w=1;w<=W;w++){//背包容量W
        if(w-wt[i]<0){ //背包没地不放了
          dp[i][w]=dp[i-1][w];
        }else{
          //装入或者不装入背包
          //进行这次装入：dp[i-1][w-wt[i-1]]+vals[i-1]表示前i-1个物品装入容量w-wt[i-1]的时候能装入的价值+第i次的价值
          //不锦进行这次装入:dp[i-1][w]:上一次物品个数重量不变时候，背包容量为w存入的价值
          dp[i][w]=Math.max(dp[i-1][w-wt[i-1]]+vals[i-1],dp[i-1][w]);

        }
      }
    }
    return dp[N][W];
  }
}
