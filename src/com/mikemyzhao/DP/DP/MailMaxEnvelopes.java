package com.mikemyzhao.DP.DP;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 16:04
 * @Description:
 * @NO:LC354:套娃信封，就是一个二维的最大子序列问题，先把宽度按照升序排列，宽度相同，按照高度的降序进行内排，剩下的对高度进行最大子序列
 * 这种解法不是最快的，应该用最长递增子序列通用解的ends数组处理
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

  public static int maxEnvelopesWithEndsArray(int[][] envelopes) {
    //a[1]是高度，a[0]是宽度
    int n = envelopes.length;
    Arrays.sort(envelopes, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b){
        return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
      }
    });
    //使用ends数组完成最长递增子序列问题
    int[] ends = new int[envelopes.length];
    ends[0] = envelopes[0][0];
    int right = 0;
    int l = 0;
    int r = 0;
    int m = 0;
    for(int i = 1; i < envelopes.length; i++){
      l = 0;
      r = right;
      while(l <= r){
        m = (l + r) / 2;
        if(envelopes[i][0] > ends[m]){
          l = m + 1;
        } else {
          r = m - 1;
        }
      }
      right = Math.max(l, right);
      ends[l] = envelopes[i][0];
    }
    return right + 1;
  }

  public static void main(String[] args) {
    int[][] arr = new int[][]{{1,1},{1,1},{1,1}};
    System.out.println(maxEnvelopesWithEndsArray(arr));
  }
}
