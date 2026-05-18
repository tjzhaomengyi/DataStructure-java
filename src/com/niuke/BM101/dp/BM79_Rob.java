package com.niuke.BM101.dp;

import java.util.Arrays;

public class BM79_Rob {
    //标准写法，按照偷的长度来写
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for(int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
    public int robCycle(int[] nums){
        //要不选第一个，要么不选第一个
        int n = nums.length;
        int max = 0;
        int[] dp = new int[n];
        dp[0] = nums[0]; // 情况1 选择第一家打劫，最后一家不能打了，卡在n-1
        for(int i = 2; i < n - 1; i++){
            //最后一家是打死也碰不到的，这里跳过最后一家，所以是小于n
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        max = dp[n - 2];
        Arrays.fill(dp, 0);
        dp[1] = nums[1]; //第0家不选，那么第1家肯定选，保证dp[1]最大
        for(int i = 2; i < n; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        max = Math.max(max, dp[n - 1]);
        return max;
    }
}
