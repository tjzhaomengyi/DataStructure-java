package com.mikemyzhao.DPAndRecursion_5.DP;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-06 10:03
 * @Description:最长回文
 * @NO:LC516 dp[i][j]表示在s[i...j]中，最长回文子序列的长度dp[i][j],i向左移动，j向右移动
 * 注意：当s[i]==s[j]时：dp[i][j]=dp[i+1][j-1]+2
 *      a****a
 *      i    j
 *      当s[i]!=s[j]时:(1)dp[i][j]=max(dp[i+1][j],dp[i][j-1])
 *      (1)ab***b
 *         i    j
 *      (2)a***ab
           i    j
 */
public class LongestCycleSubArr {
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int dp[][] = new int[n][n];
    dp[0][0]=1;
    for(int i=1;i<n;i++){
      dp[i][i]=1;
    }
    for(int i=n-2;i>=0;i--){//注意下标i和j的移动方式，
      for(int j=i+1;j<=n-1;j++){
        if(s.charAt(i)==s.charAt(j)){
          dp[i][j]=dp[i+1][j-1]+2;
        }else{
          dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
        }
      }
    }
    return dp[0][n-1];
  }
  //最长回文降维写法
  public int LPSOneDim(String s){
    int n = s.length();
    int[] dp = new int[n];
    Arrays.fill(dp,1);
    for(int i=n-1;i>=0;i--){
      int pre = 0;
      for(int j=i+1;j<=n-1;j++){
        int tmp = dp[j];
        if(s.charAt(i)==s.charAt(j)){
           dp[j] = dp[j]+2;
        }else{
          dp[j] = Math.max(dp[j],dp[j-1]);
        }
        pre=tmp;
      }
    }
    return dp[n-1];
  }

  public static void main(String[] args) {
    System.out.println(new LongestCycleSubArr().longestPalindromeSubseq("bbbab"));
  }
}
