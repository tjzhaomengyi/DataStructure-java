package com.niuke.BM101.dp;

public class BM76_RegexMathPointStar {
    boolean isMath(String s, String p){
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        return true;
    }
}
