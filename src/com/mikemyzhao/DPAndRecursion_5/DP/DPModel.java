package com.mikemyzhao.DPAndRecursion_5.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-06 10:09
 * @Description:DP模板，可以一维，二维
 */
public class DPModel {
  int oneDimDP(String s){
    int n = s.length();
    int[] dp = new int[n+1];
    for(int i=1;i<n;i++){
      for(int j =0;j<i;j++){
        dp[i]=Math.max(dp[i-1],dp[i]);
      }
    }
    return dp[n];
  }

  int twoDimDP(String s1,String s2){
    int m = s1.length();int n = s2.length();
    int[][] dp = new int[m+1][n+1];
    /**如果一个字符串或者数组的两个指针，一前一后，最外层从后往前，里面一层从前往后**/
    for(int i=1;i<=n;i++){
      for(int j=1;j<=m;j++){
        if(dp[i-1]==dp[j-1]){
          dp[i][j]=dp[i-1][j-1];
        }else{
          dp[i][j]=Math.max(dp[i-1][j-1],dp[i][j]);
        }
      }
    }
    return dp[m][n];
  }


}
