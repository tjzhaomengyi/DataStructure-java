package com.mikemyzhao.DP.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-06 8:20
 * @Description:编辑距离，面试常考，给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：插入一个字符删除一个字符替换一个字符
 * 动态规划解法：使用dp[i][j]表示word1中第i位，word2中第j位时执行什么操作，框架
 * if s1[i] == s2[j]:
 *  什么都不做
 * else: 三选一
 *  插入/删除/替换
 * @NO:LC72
 */
public class EditDistance {
  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    int[][] dp = new int[m+1][n+1];
    for(int i=0;i<=m;i++){
      dp[i][0]=i;
    }
    for(int j=0;j<=n;j++){
      dp[0][j]=j;
    }
    for(int i=1;i<=m;i++){ //其实这里字符串是在遍历长度
      for(int j=1;j<=n;j++){
        if(word1.charAt(i-1)==word2.charAt(j-1)){
          dp[i][j] = dp[i-1][j-1];
        }else{
          dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
        }
      }
    }
    return dp[m][n];
  }

  public static void main(String[] args) {
    System.out.println(new EditDistance().minDistance("horse","ros"));
  }
}
