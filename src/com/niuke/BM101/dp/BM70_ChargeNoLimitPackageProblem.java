package com.niuke.BM101.dp;


import java.util.Arrays;

/**
 * 无限背包问题，就是能拿的东西有无限个，不限制拿多少凑上就行
 *  这种问题的解决方法，就是让钱币每次取出一张，然后从这个面值coin 每涨1元，凑到aim，这个过程中不断修改dp[j]，就能最优解
 *  for(j = coin; j <= aim; j++) 每次的dp[j] = Math.min(dp[j - coin] + 1, dp[j]) 隐含就是张数的统计，表示凑 j 元 需要多少张钞票，从j - coin 凑到 j 正好加了一张coin
 */
public class BM70_ChargeNoLimitPackageProblem {
    public int chargeNoLimit(int[] arr, int aim){
        if(arr == null || arr.length == 0) return -1;
        Arrays.sort(arr);
        int[] dp = new int[aim + 1];
        for(int i = 1; i <= aim; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int coin : arr){
            for(int j = coin; j <= aim; j++){
                if(dp[j - coin] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
                }
            }
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }
}
