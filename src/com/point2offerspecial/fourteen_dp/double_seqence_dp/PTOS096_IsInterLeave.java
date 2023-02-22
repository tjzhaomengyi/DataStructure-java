package com.point2offerspecial.fourteen_dp.double_seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 12:37
 * @Description:
 */
public class PTOS096_IsInterLeave {
  public boolean isInterleave(String s1, String s2, String s3) {
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    char[] str3 = s3.toCharArray();
    int n = s1.length();
    int m = s2.length();
    int k = s3.length();

    //dp对应的下标表示个数
    boolean[][] dp = new boolean[n + 1][m + 1];//表示从str1 和 str2中拿走几个字符凑str3
    dp[0][0] = true; //一个都不拿，凑空当然是true
    for(int i = 1; i < n + 1; i++){
      int t = i + 0 - 1;//t表示在str3中的下标
      dp[i][0] = str1[i - 1] == str3[t] && dp[i - 1][0];
    }

    for(int j = 1; j < m + 1; j++){
      int t = j + 0 - 1;
      dp[0][j] = str2[j - 1] == str3[t] && dp[0][j - 1];
    }

    for(int i = 1; i < n + 2; i++){
      for(int j = 1; j < m + 2; j++){
        char c1 = str1[i - 1];
        char c2 = str2[j - 1];
        char c3 = str3[i - 1 + j - 1 - 1];
        //第i和j选择来自c1 或者 c2
        dp[i][j] = (c1 == c3 && dp[i - 1][j] ) || (c2 == c3 && dp[i][j - 1]);
      }
    }
    return dp[n][m];
  }



}
