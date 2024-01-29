package com.book.point2offerspecial.fourteen_dp.path_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 17:14
 * @Description:
 */
public class PTOS098_UniquePath {
  public int uniquePaths(int m, int n) {
    if(m == 0 || n == 0) return 1;
    int[][] dp = new int[m][n];//表示到[i,j]位置的方法
    //从开始走到同一行/同一列的末尾方法是1
    dp[0][0] = 1;
    for(int i = 0; i < m; i++){
      dp[i][0] = 1;
    }
    for(int j = 0; j < n; j++){
      dp[0][j] = 1;
    }

    for(int i = 1; i < m; i++){
      for(int j = 1; j < n; j++){
        //填表就完了
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }
}
