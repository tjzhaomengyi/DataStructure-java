package com.book.zuoshen.InterviewGuide.chpt4.stringdp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/6 14:46
 * @Description:最长公共子序列
 */
public class LongestCommonSubStr {
    //技巧三种情况：dp[i][j]表示str1[0..i]和str2[0..j]得到的最长公共子序列的长度，
    // (1)情况一：str1[0..i-1]和str2[0..j]得到最长公共子序列 dp[i-1][j]
    // (2)情况二: str1[0..i] 和 str2[0..j-1]得到最长公共子序列 dp[i][j-1]
    // (3)情况三：str1[0..i] 和 str2[0..j] 得到最长公共子序列 dp[i-1][j-1] + 1
    public int[][] getDP(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for(int i = 1; i < str1.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for(int j = 1; j < str2.length; j++){
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for(int i = 1; i < str1.length; i++){
            for(int j = 1; j < str2.length; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if(str1[i] == str2[j]){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }
}
