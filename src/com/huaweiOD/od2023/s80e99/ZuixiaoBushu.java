package com.huaweiOD.od2023.s80e99;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 21:37
 * @Description:
 */
public class ZuixiaoBushu {
  static int minSteps = Integer.MAX_VALUE;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] lines = in.nextLine().split(" ");
      int[] nums = new int[lines.length];
      for(int i = 0; i < lines.length; i++){
        if(i == 0){
          nums[0] = Math.min(Integer.valueOf(lines[0]), lines.length / 2 - 1);
        }else {
          nums[i] = Integer.valueOf(lines[i]);
        }
      }
      boolean can = process(nums, 0, 0);
      System.out.print(can == true ? minSteps : -1);
    }
  }

  public static boolean process(int[] nums, int index, int steps){
    int len = nums.length;
    boolean ans = false;
    if(index == len - 1){
      minSteps = Math.min(minSteps, steps);
      return true;
    } else if(index > len - 1){
      return false;
    }
    if(index == 0){
      for(int i = 1; i <= nums[0]; i++){
        ans = process(nums, i, steps + 1);
      }
    } else {
      ans = process(nums, index + nums[index], steps + 1);
    }
    return ans;
  }
}
