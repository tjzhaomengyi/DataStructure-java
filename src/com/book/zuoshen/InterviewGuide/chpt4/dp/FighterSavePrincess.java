package com.book.zuoshen.InterviewGuide.chpt4.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/8 12:47
 * @Description:龙与地下城，求骑士的开始血量最少是多少
 */
public class FighterSavePrincess {
    public int minHP(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 1;
        }
        int N = m.length;
        int M = m[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = m[N - 1][M - 1] > 0 ? 1 : 1 - m[N - 1][M - 1];
        for(int j = M - 2; j >= 0; j--){
            dp[N - 1][j] = Math.max(dp[N - 1][j + 1] - m[N - 1][j], 1); //最后一行
        }
        for(int i = N - 2; i >= 0; i--){
            dp[i][M - 1] = Math.max(dp[i + 1][M - 1] - m[i][M - 1], 1);
        }
        for(int i = N - 2; i >= 0; i--){
            for(int j = M - 2; j >= 0; j--){
                int right = Math.max(dp[i][j + 1] - m[i][j], 1);
                int up = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(right, up);
            }
        }
        return dp[0][0];
    }
}
