package com.niuke.BM101.dp;

public class BM67_PathCount {
    /**
     * 从左到右数有多少路径可以走，用这个优雅的方法
     */
    public int uniquePath(int m, int n){
        // 这里直接设置第一行和第一列都为0，这样避免赋值的麻烦事情
        // 然后把坐标都向下同时推1，比如dp[1][1]就表示在（0，0）位置到达这个点的方法数就是1，即站着不动
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i == 1 && j == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }
}
