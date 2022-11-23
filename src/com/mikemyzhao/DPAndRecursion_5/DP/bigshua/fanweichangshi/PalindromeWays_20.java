package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.fanweichangshi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-04 14:31
 * @Description:返回有多少子序列回文子序列
 */
public class PalindromeWays_20 {
  public static int ways1(String str){
    if(str == null || str.length() == 0){
      return 0;
    }
    char[] s = str.toCharArray();
    int n = s.length;
    int[][] dp = new int[n][n];//表示从i到j回文子序列的长度
    for(int i = 0; i < n; i++){
      dp[i][i] = 1;
    }
    //斜对接线上面
    for(int i = 0; i < n - 1; i++){
      dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;//技巧：子序列就是两个相等就是三个aa(a,a,aa三个),如果不相等就是两个(a，b)
    }
    for(int L = n - 3; L >= 0; L--){
      for(int R = L + 2; R < n; R++){
        //情况[L][R]
        //a(1)L没有，R没有
        //b(2)L可以没有也可以有，R必须有
        //c(3)L必须有，R可以有没有
        //dp=a+b+c
        //dp[L-1][R-1] = a ,dp[L+1][R] = a + b,dp[L][R-1] = c + a
        //dp[L+1][R] + dp[L][R-1] - dp[L-1][R-1] = a+b+c
        //(4)L和R必须都有
        dp[L][R] =dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
        if (s[L] == s[R]){
          dp[L][R] += dp[L + 1][R - 1] + 1;
        }
      }
    }
    return dp[0][n-1];
  }

}
