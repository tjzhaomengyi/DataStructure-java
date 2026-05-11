package com.niuke.BM101.dp;

public class BM71_LIS {
    public int LIS(int[] nums){
        int max = 1;
        //最长上升子序列，设置成下标即可，这个不需要考虑为0的边界
        int[] dp = new int[nums.length];
        for(int i = 0; i <= nums.length; i++){
            dp[i] = max;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] > max) max = dp[i];
        }
        return max;
    }

}
