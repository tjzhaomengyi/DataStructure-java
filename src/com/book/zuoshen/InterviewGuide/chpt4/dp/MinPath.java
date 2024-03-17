package com.book.zuoshen.InterviewGuide.chpt4.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 17:28
 * @Description:
 */
public class MinPath {
    public int minPathSum(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for(int j = 1; j < col; j++){
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    //动态规划的状态空间压缩,先从第一行开始，然后每个位置可能来自左侧（新求出来的状态）或者从上面过来的状态，然后滚动该数组
    public int minPathSum2(int[][] m){
        if(m == null || m.length == 0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length; //行数是不是大于或者等于列数
        int[] arr = new int[less]; //辅助数组的长度是行数与列数的最小值
        arr[0] = m[0][0];
        //初始化arr数组以最短长度为基准
        for(int i = 1; i < less; i++){
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
        }
        for(int i = 1; i < more; i++){
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for(int j = 1; j < less; j++){//压缩的内部
                arr[j] = Math.min(arr[j - 1], arr[j]) + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }

}
