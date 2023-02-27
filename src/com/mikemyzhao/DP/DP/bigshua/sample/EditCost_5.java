package com.mikemyzhao.DP.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 10:46
 * @Description:编辑距离问题
 */
public class EditCost_5 {
  //解法：样本对应模型，根据结尾划分可能性，并且dp表示的是前缀长度的意思,把word1变成word2
  public static int minCost(String word1,String word2, int d, int a, int r){
    if(word1 == null || word2 == null){
      return 0;
    }
    char[] str1 = word1.toCharArray();
    char[] str2 = word2.toCharArray();
    int N = str1.length + 1;
    int M = str2.length + 1;
    int[][] dp = new int[N][M];//取前N个字符作为前缀变成M个字符的str2
    //初始化
    for (int i = 1; i < N; i++){
      dp[i][0] = d * i;
    }
    for(int j = 1; j < M; j++){
      dp[0][j] = a * j;
    }
    for(int i = 1; i < N; i++){
      for(int j = 1; j < M; j++){
        dp[i][j] = str1[i - 1] == str2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + r;
        dp[i][j] = Math.min(dp[i][j],dp[i][j - 1] + a);
        dp[i][j] = Math.min(dp[i][j],dp[i-1][j] + d);
      }
    }
    return dp[N-1][M-1];
  }

}
