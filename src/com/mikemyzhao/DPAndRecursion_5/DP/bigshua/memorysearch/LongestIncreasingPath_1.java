package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.memorysearch;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 10:46
 * @Description:矩阵中最长递增路径,每个点都有可能成为最好的点，所以两个遍历
 * @LC329
 */
public class LongestIncreasingPath_1 {
  public static int longestIncreasingPath(int[][] matrix){
    int ans = 0;
    int[][] dp = new int[matrix.length][matrix[0].length];
    for(int i=0;i<matrix.length;i++){
      for(int j=0;j<matrix[0].length;j++){
        ans = Math.max(ans,process(matrix,i,j,dp));
      }
    }
    return ans;
  }

  private static int process(int[][] m,int i,int j,int[][] dp) {
    if(dp[i][j]!=0){
      return dp[i][j];
    }

    //四个方向哪个方向大往哪个方向递归下去
    int up = i > 0 && m[i-1][j] >m[i][j] ? process(m,i-1,j,dp):0;
    int down = i < m.length-1 && m[i+1][j] > m[i][j] ? process(m,i+1,j,dp):0;
    int left = j > 0 && m[i][j] < m[i][j-1] ? process(m,i,j-1,dp):0;
    int right = j< (m[0].length-1) && m[i][j+1] > m[i][j] ? process(m,i,j+1,dp):0;
    int ans = Math.max(Math.max(up,down),Math.max(left,right))+1;
    dp[i][j]=ans;
    return ans;
  }

}
