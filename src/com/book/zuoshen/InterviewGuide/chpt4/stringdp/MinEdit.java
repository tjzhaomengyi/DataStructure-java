package com.book.zuoshen.InterviewGuide.chpt4.stringdp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/7 16:55
 * @Description:
 */
public class MinEdit {
    public int minCost(String str1, String str2, int ic, int dc, int rc){
        if(str1 == null || str2 == null){
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int M = chs1.length;
        int N = chs2.length;
        int[][] dp = new int[M + 1][N + 1];//已经修改好i到j长度的子字符串相等时候，消耗了多少代价
        //dp[0][0]=0//两个长度都为0，代价是0
        for(int i = 0; i < M + 1; i++){
            dp[i][0] = dc * i;
        }
        for(int j = 0; j < N + 1; j++){
            dp[0][j] = ic * j;
        }
        for(int i = 1; i < M + 1; i++){
            for(int j = 1; j < N + 1; j++){
                //修改
                if(chs1[i - 1] == chs2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                //删除
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + dc);
            }
        }
        return dp[M][N];
    }
}
