package com.mikemyzhao.DP.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-08 18:56
 * @Description:参考零钱兑换II，注意求方法总和用上一次+这一次
 * @NO:LC518，总金额amount(背包大小)，硬币coins个数不限(物品数量)，用coins凑amount(用物品装书包),求组合数(有多少中装书包办法)
 *  dp[i][j]表示若只使用前i个物品，当背包容量为j时，有dp[i][j]中装法。
 *  硬币：dp[i][j]表示若只使用前i个硬币的面值，凑出金额j，有dp[i][j]种方法
 *  basecase dp[0][j]=0,dp[i][0]=1
 *  求解dp[N][amount]
 *  转移过程dp[i][j]=dp[i-1][j]表示不用放入新面值，即可得到解
 *  dp[i][j]=dp[i][j-coins[i-1]],表示如果要用前i凑出j金额，那么就得知道用i凑出j-coins[i-1]金额的办法
 *  (比如如果用2凑5，现在已经知道凑出3的方法，那么再加这个2就行了)
 */
public class TotalBag {
  int change(int amount,int[] coins){
    int n = coins.length;
    int[][] dp = new int[n+1][amount+1];
    //base case
    for(int i=0;i<=n;i++){
      dp[i][0]=1;
    }
    for(int i=1;i<=n;i++){
      for(int j=1;j<=amount;j++){
        if(j>=coins[i-1]){//可以放进这枚硬币
          dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];//总共方法就是不用这个硬币能凑到j的方法数 加上 用这枚硬币凑到的方法数
        }else{
          //不能放入
          dp[i][j]=dp[i-1][j];
        }
      }
    }
    return dp[n][amount];
  }
}
