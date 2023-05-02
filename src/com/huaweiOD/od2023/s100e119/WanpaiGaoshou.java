package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 18:42
 * @Description:
 */
public class WanpaiGaoshou {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.next();
      String[] arr = str.split(",");
      int[] nums = new int[arr.length];
      for(int i = 0 ; i < arr.length; i++){
        nums[i] = Integer.valueOf(arr[i]);
      }
      System.out.println(getMax(nums));
    }
  }

  public static int getMax(int[] nums){
    int[] dp = new int[nums.length];
    dp[0] = nums[0] > 0 ? nums[0] : 0;
    for(int i = 1; i < nums.length; i++){
      if(i < 3){
        dp[i] = Math.max(0, dp[i - 1] + nums[i]);
      } else {
        dp[i] = Math.max(dp[i - 3], dp[i - 1] + nums[i]);
      }
    }
    return dp[nums.length - 1];
  }
}
