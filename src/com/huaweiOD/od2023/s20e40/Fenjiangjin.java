package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 14:14
 * @Description:
 */
public class Fenjiangjin {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int n = in.nextInt();
      int[] nums = new int[n];
      for(int i = 0; i < n; i++){
        nums[i] = in.nextInt();
      }
      int[] ans = fenJiangjin(nums);
      for(int i = 0; i < ans.length; i++){
        System.out.print(ans[i] + " ");
      }
      System.out.println();
    }
  }

  public static int[] fenJiangjin(int[] nums){
    int[] ans = new int[nums.length];
    for(int i = 0; i < nums.length; i++){
      int n = nums[i];
      if(i == nums.length - 1){
        ans[i] = nums[i];
      }
      for(int j = i + 1; j < nums.length; j++){
        int m = nums[j];
        if(m > n){
          ans[i] = (j - i) * (m - n);
          break;
        } else {
          ans[i] = nums[i];
        }
      }
    }
    return ans;
  }
}
