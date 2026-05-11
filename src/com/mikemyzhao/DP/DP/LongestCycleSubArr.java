package com.mikemyzhao.DP.DP;

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
  //要知道dp[2][5] 要先知道 dp[3][4] 所以i要从大到小遍历，j要从小到大遍历
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int start = 0;
    int maxLen = 1;

    //单个字符一定是回文子串
    for(int i = 0; i < n; i++){
      dp[i][i] = true;
    }
    // i从下往上
    for(int i = n - 1; i >= 0; i--){
      for(int j = i + 1; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (j - i < 3) { // aba
            dp[i][j] = true;
          } else {
            dp[i][j] = dp[i + 1][j - 1];
          }
        }
        //更新最大长度
        if (dp[i][j] && j - i + 1 > maxLen) {
          maxLen = j - i + 1;
          start = i;
        }
      }
    }
    return maxLen; //如果返回string，返回s.substring(start, start + maxLen);
  }


  public static void main(String[] args) {
    System.out.println(new LongestCycleSubArr().longestPalindromeSubseq("bbbab"));
  }
}
