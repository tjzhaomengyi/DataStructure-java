package com.point2offerspecial.fifteen_graph.all_path;


/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 10:28
 * @Description:
 */
public class PTOS112_LongestIncreasingPath {
  //技巧：因为要记录最长路径的长度，所以使用DFS
  //太难了~直接记住例子吧，这个是用dp代替递归的经典题目
  public int longestIncreasingPath(int[][] matrix) {
    if(matrix.length == 0 || matrix[0].length == 0 || matrix == null) return 0;

    int ans = 0;
    int N = matrix.length;
    int M = matrix[0].length;
    int[][] dp = new int[N][M];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        ans = Math.max(ans, dfs(matrix, i, j, dp));
      }
    }
    return ans;
  }

  private int dfs(int[][] m, int i, int j, int[][] dp){
    if(dp[i][j] != 0){
      return dp[i][j];
    }
    int up = i > 0 && m[i][j] < m[i - 1][j] ? dfs(m, i - 1, j ,dp) : 0;
    int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? dfs(m, i + 1, j, dp) : 0;
    int left = j > 0 && m[i][j] < m[i][j - 1] ? dfs(m, i, j - 1, dp) : 0;
    int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? dfs(m, i, j + 1, dp) : 0;
    int ans = Math.max(Math.max(Math.max(up, down), left), right) + 1;
    dp[i][j] = ans;
    return ans;
  }
}
