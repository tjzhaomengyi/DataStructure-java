package com.mikemyzhao.DP.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 17:33
 * @Description:S和T两个字符串，求s中有多少子序列面值等于T【子序列要求，必须以当前字符结尾[i],前面的顺序不可改变，但是可以不连续】
 */
public class SubSequenceEqualsString_17 {
  public static int dp(String S,String T){
    char[] s = S.toCharArray();
    char[] t = T.toCharArray();
    int N = s.length;
    int M = t.length;
    int[][] dp= new int[N][M];//以当前s中以[i]字符结尾有多少能拼成以t中以[j]结尾的字符串面值
    dp[0][0] = s[0] == t[0] ? 1 : 0;
    for(int i = 1; i < N; i++){
      dp[i][0] = s[i] == t[0] ? (dp[i - 1][0] + 1) : dp[i - 1][0];
    }
    for(int i = 1; i < N; i++){
      for(int j = 1; j <= Math.min(i, M - 1); j++){
        dp[i][j] = dp[i - 1][j];
        if(s[i] == t[j]){
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
        }
      }
    }
    return dp[N - 1][M - 1];
  }
}
