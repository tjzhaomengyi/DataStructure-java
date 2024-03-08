package com.weeklyproblems.week2021_12_1;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/7 09:29
 * @Description:
 */
public class Code02_KeyBoardInputA {
    //dp[i],i步骤以内获得最多的A是多少
    //要证明的结论：(1)前六步只有打A最经济，剩下步骤就按照结论（2）来递推
    // （2）如果前面5步全是粘贴的话，这个操作不是最优操作
    //12-s个，13-all,14-copy,15到19都是paste，最后又6s个，但是粘贴板内存又1s个
    //12-s个,13-all,14-copy,15-paste(2s个)，16-all,17-copy,18-paste(paste,4s个),19-paste(6s个),此时粘贴板内存中是2s个
    // 所以要不是从第一次p粘贴来的，要不是第二次，要不是第三次，要不是第四次，这个最优解肯定是从这个步骤来的
    //如果是第一次粘贴 dp[i - 3] * 2; i-3->全选->复制->粘贴
    //如果是第二次粘贴 3* dp[i - 4]; -4 -3全选->-2复制->-1粘贴(2s)->粘贴(3s)
    public static int maxA(int n){
        //dp[0]表示1步以内的最优解
        int[] dp = new int[n];
        for(int i = 0; i < 6 && i < n; i++){
            dp[i] = i + 1;
        }
        for(int i = 6; i < n; i++){
            dp[i] = Math.max(Math.max(dp[i - 3] * 2, dp[i - 4] * 3), Math.max(dp[i - 5] *4, dp[i - 6] * 5));
        }
        return dp[n - 1];//n步错以为
    }
}
