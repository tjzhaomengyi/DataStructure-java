package com.niuke.BM101.dp;

public class BM75_EditDistance {
    /**
     * 在 str2 的 j-1 位置变成 str1 的 i-1 位置的字符最少需要多少步操作
     * @param str1
     * @param str2
     * @return
     */
    public int minEditDistance(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else { //如果这两个字符不一样，进行三个操作，以对齐str1的字符为目标,,只能从小表大，因为这个循环是从小到大遍历的
                    //1、str2 添加一个字符串(abc <- ab) dp[3][2] 需要 dp[2][2] + 1 , 2、str2 删除一个等同于str1 加一个（ab <- abc） dp[2][3] 需要 dp[]
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;

                }
            }
        }
        return dp[m][n];
    }
}
