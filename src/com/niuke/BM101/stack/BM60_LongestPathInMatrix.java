package com.niuke.BM101.stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 求矩阵递增序列的最大长度，
 * 这道题没法用纯回溯的方法来做，这样会超出内存限制
 */

public class BM60_LongestPathInMatrix {
    int[][] dp; //这里dp只做记录，没有改变，所以使用全局变量就行，在递归函数种不用当作参数传进去
    //简单记忆动态规划
    public int solve(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n]; //表示从当前点到最终的最长递增序列长度

        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dfs(matrix, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j){
        if(dp[i][j] != 0) return dp[i][j]; //表明这个点已经求完了最长递增序列长度，不需要求了！

        int max = 1;
        int[][] dirs = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        for(int[] dir : dirs){
            int ni = i + dir[0];
            int nj = j + dir[1];
            if(ni >=0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length
                && matrix[ni][nj] > matrix[i][j]){
                max = Math.max(max, 1 + dfs(matrix, ni, nj));
            }
        }
        dp[i][j] = max;
        return max;
    }
}
