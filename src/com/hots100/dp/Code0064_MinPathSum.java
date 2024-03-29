package com.hots100.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-08 3:41 下午
 * @Description:
 */
public class Code0064_MinPathSum {

  public int minPathSum(int[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length ==0  || grid[0] == null) return 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dp = new int[n][m];
    dp[0][0] = grid[0][0];
    for(int j = 1; j < m; j++){//列初始化
      dp[0][j] = dp[0][j-1] + grid[0][j];
    }
    for(int i = 1; i < n; i++){//行初始化
      dp[i][0] = dp[i-1][0] + grid[i][0];
    }
    for(int i = 1; i < n; i++){
      for(int j = 1; j < m; j++){
        dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + grid[i][j];
      }
    }

    return dp[n - 1][m - 1];
  }
}
