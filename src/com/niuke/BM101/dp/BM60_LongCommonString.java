package com.niuke.BM101.dp;

public class BM60_LongCommonString {
    /**
     * 建议以长度为核心来考虑dp转换,这样可以省去初始的边界处理，这样写最标准
     */
    public int LCS(String s1, String s2){
        if(s1 == null || s2 == null) return -1;

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        // 这样设置的时候长度为0的时候，默认dp转移是0，不需要单独写了
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 找到最长子序列
     * @param s1
     * @param s2
     * @return
     */
    public String LCSAndReturn(String s1, String s2){
        if(s1 == null || s2 == null) return null;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j]  = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;

        //倒着找回去，从后面往前找
        while(i > 0 && j > 0){
            if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if(dp[i - 1][j] > dp[i][j - 1]){
                    i--;
                } else {
                    j--;
                }
            }
        }
        return sb.reverse().toString();
    }
}
