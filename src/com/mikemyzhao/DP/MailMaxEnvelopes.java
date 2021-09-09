package com.mikemyzhao.DP;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 16:04
 * @Description:
 * @NO:LC354:套娃信封，就是一个二维的最大子序列问题，先把宽度按照升序排列，宽度相同，按照高度的降序进行内排，剩下的对高度进行最大子序列
 */
public class MailMaxEnvelopes {
  int maxEnvelopes(int[][] envelops){
    int n = envelops.length;
    Arrays.sort(envelops, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return a[0]==b[0] ? b[1]-a[1]:a[0]-b[0];//如果宽度相等，高度升序排列；如果宽度不等，按照宽度降序
      }
    });
    //对高度寻找LIS
    int[] height = new int[n];
    for(int i = 0; i<n;i++){
      height[i] = envelops[i][1];
    }
    int res = lengthOfLIS(height);
    return res;
  }
  //默写一些最长子序列
  int lengthOfLIS(int[] nums){
    int res = 1;
    int[] dp = new int[nums.length];
    Arrays.fill(dp,1);
    for(int i =0;i<nums.length;i++){
      for(int j = 0;j<i;j++){
        if(nums[i]>nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      res = Math.max(dp[i],res);
    }
    return res;
  }
}
