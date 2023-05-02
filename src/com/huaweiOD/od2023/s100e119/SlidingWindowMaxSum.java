package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 20:11
 * @Description:
 */
public class SlidingWindowMaxSum {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int len = in.nextInt();
      int[] nums = new int[len];
      for(int i = 0; i < len; i++){
        nums[i] = in.nextInt();
      }
      int w_len = in.nextInt();

      int max = getWindowMax(nums, w_len);
      System.out.println(max);
    }
  }

  public static int getWindowMax(int[] nums, int w_len){
    int max = Integer.MIN_VALUE;
    int left = 0;
    int right = 0;
    int sum = 0;
    while(right < nums.length){
      sum += nums[right];
      if(right - left < w_len){
        right++;
      } else {
        sum -= nums[left];
        right++;
        left++;
      }
      max = Math.max(max, sum);
    }
    return max;
  }


}
