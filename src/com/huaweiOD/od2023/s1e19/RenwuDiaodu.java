package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 16:05
 * @Description:
 */
public class RenwuDiaodu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int max = Integer.valueOf(in.nextLine());
      int len = Integer.valueOf(in.nextLine());
      String[] strs = in.nextLine().split(" ");
      int[] nums = new int[len];
      for(int i = 0; i < strs.length; i++){
        nums[i] = Integer.valueOf(strs[i]);
      }
      int ans = solve(max, len, nums);
      System.out.println(ans);
    }
  }

  public static int solve(int max, int len, int[] nums){
    int leftVal = 0;
    for(int i = 0; i < len; i++){
      int val = nums[i];
      if(val < 1 || val > 10000){
        System.out.println("input error");
      }
      leftVal = Math.max(0, leftVal + val - max); //把这次剩下的往后推
    }
    int result = len;
    if(leftVal % max != 0){
      result += leftVal / max + 1;
    } else {
      result += leftVal / max;
    }
    return result;
  }

}
