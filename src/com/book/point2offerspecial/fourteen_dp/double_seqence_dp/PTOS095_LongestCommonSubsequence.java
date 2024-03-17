package com.book.point2offerspecial.fourteen_dp.double_seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 11:26
 * @Description:
 */
public class PTOS095_LongestCommonSubsequence {
  public int longestCommonSubsequence(String text1, String text2) {
    int n = text1.length();
    int m = text2.length();
    char[] s1 = text1.toCharArray();
    char[] s2 = text2.toCharArray();
    if(m == 0 || n == 0) return 0;

    int[][] dp = new int[n][m];//s1从0到i,s2从0到j的最长公共子序列是多长
    dp[0][0] = s1[0] == s2[0] ? 1 : 0;
    //初始化表的两头
    for(int i = 1; i < n; i++){
      dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
    }
    for(int j = 1; j < m; j++){
      dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
    }

    for(int i = 1; i < n; i++){
      for(int j = 1; j < m; j++){
       int p1 = dp[i - 1][j];//以[i][j]结尾
       int p2 = dp[i][j - 1];
//        int p3 = s1[i] == s2[j] ? (1 + dp[i - 1][j - 1]) : 0;
//        dp[i][j] = Math.max(p1, Math.max(p2, p3));
        if(s1[i] == s2[j]){
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(p1, p2);
        }
      }
    }
    return dp[n - 1][m - 1];
  }
}
