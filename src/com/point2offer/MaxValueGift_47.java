package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 20:52
 * @Description:
 */
public class MaxValueGift_47 {
  public int maxValue(int[][] grid) {
    //向右或者向下i,j->i,j+1|i+1,j
    int m=grid.length,n=grid[0].length;
    int[][] dp = new int[m+1][n+1];
    dp[0][0]=grid[0][0];
    for(int i=1;i<m+1;i++){
      dp[i][0]=dp[i-1][0]+grid[i][0];
    }
    for(int j=1;j<n+1;j++){
      dp[0][j]=dp[0][j-1]+grid[0][j];
    }
    for(int i=0;i<m+1;i++){
      for(int j=0;j<n+1;j++){
        dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
      }
    }
    return grid[m-1][n-1];
  }
}
