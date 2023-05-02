package com.daybyday.recursion;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-27 12:21
 * @Description:
 */
public class LC1144_ZigZagArray {
  public int movesToMakeZigzag(int[] nums) {
    return Math.min(help(nums, 0), help(nums, 1)); // 技巧:调整奇数位 或者 调整偶数位, 两条腿走路。先看偶数，再看奇数开始
  }

  public int help(int[] nums, int pos){
    int res = 0;
    for(int i = pos; i < nums.length; i+=2){
      int a = 0;
      if(i - 1 >= 0){
        a = Math.max(a, nums[i] - nums[i - 1] + 1); //每次减最大的，保证奇数位/或者偶数位为最大，操作次数就是当前a 和 当前相邻差要执行的操作数
      }
      if(i + 1 < nums.length){
        a = Math.max(a, nums[i] - nums[i + 1] + 1);
      }
      res += a;
    }
    return res;
  }
}
