package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 11:35
 * @Description:
 */
public class ZhaoZhongdian {
  static int minStep = Integer.MAX_VALUE;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[str.length];
      for(int i = 0; i < str.length; i++){
        if(i == 0){
          nums[i] = Math.min(Integer.valueOf(str[i]), nums.length / 2 - 1);
        }else {
          nums[i] = Integer.valueOf(str[i]);
        }
      }
      boolean can = process(nums, 0 , 0);
      System.out.println(can == true ?  minStep : -1);
    }
  }
  public static boolean process( int[] nums, int index, int cur_steps){
    int len = nums.length;
    boolean ans = false;
    if(index == len - 1){
      minStep = Math.min(cur_steps, minStep);
      return true;
    } else if(index > len - 1){
      return false;
    }
    if(index == 0){
      for(int i = 1; i <= nums[0]; i++){
        ans = process(nums, i, cur_steps + 1);
      }
    } else {
      ans = process(nums, index + nums[index], cur_steps + 1);
    }

    return ans;
  }
}
